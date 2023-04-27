package com.taw.polybank.service;

import com.taw.polybank.controller.atm.TransactionFilter;
import com.taw.polybank.dao.*;
import com.taw.polybank.dto.*;
import com.taw.polybank.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    public List<TransactionDTO> findByBankAccountByBankAccountId(BankAccountDTO bankAccount) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByIban(bankAccount.getIban()).orElse(null);
        List<TransactionEntity> transactionEntities = transactionRepository.findByBankAccountByBankAccountId(bankAccountEntity);
        return entityListToDTO(transactionEntities);
    }

    public List<TransactionDTO> entityListToDTO (List<TransactionEntity> transactionEntityList){
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for(TransactionEntity transactionEntity : transactionEntityList){
            transactionDTOList.add(transactionEntity.toDTO());
        }
        return transactionDTOList;
    }

    public List<TransactionDTO> filter(BankAccountDTO bankAccount, TransactionFilter filter) {
        Timestamp begin = Timestamp.valueOf(filter.getTimestampBegin().toLocalDate().atTime(0,0));
        Timestamp end = Timestamp.valueOf(filter.getTimestampEnd().toLocalDate().atTime(23,59));

        List<ClientEntity> clients = clientRepository.findByNameOrSurname(filter.getTransactionOwner());
        BenficiaryEntity beneficiary = beneficiaryRepository.findByIban(filter.getBeneficiaryIban()).orElse(null);

        BankAccountEntity bankAccountEntity = bankAccountRepository.findByIban(bankAccount.getIban()).orElse(null);
        List<TransactionEntity> transactions;

        if(filter.getTransactionOwner().isBlank() || filter.getBeneficiaryIban().isBlank()){
            if(filter.getTransactionOwner().isBlank()){
                if(filter.getBeneficiaryIban().isBlank()){
                    transactions = transactionRepository.filterByBankAccount_TimestampRange_Amount(bankAccountEntity,begin,end, filter.getAmount());
                }else {
                    transactions = transactionRepository.filterByBankAccount_TimestampRange_beneficiaryIBAN_Amount(bankAccountEntity,begin,end,beneficiary, filter.getAmount());
                }
            }else{
                transactions = transactionRepository.filterByBankAccount_TimestampRange_TransactionOwner_Amount(bankAccountEntity,begin,end,clients, filter.getAmount());
            }
        }else{
            transactions = transactionRepository.filterByBankAccount_TimestampRange_TransactionOwner_beneficiaryIBAN_Amount(bankAccountEntity, begin, end, clients, beneficiary, filter.getAmount());
        }
        return entityListToDTO(transactions);
    }


    public void makeTransaction(double amount, String ibanReceptor, String nameReceptor, BadgeDTO finalBadgeDTO, BadgeDTO emisorBadgeDTO, BankAccountDTO bankAccountEmisorDTO, ClientDTO clientDTO) {

        BadgeEntity finalBadge = badgeRepository.findById(finalBadgeDTO.getId()).orElse(null);

        ClientEntity client = clientRepository.findByDNI(clientDTO.getDni());

        BenficiaryEntity beneficiary = getOrMakeBeneficiaryEntity(ibanReceptor, nameReceptor, finalBadge);

        //Make (if neccessary) a currency exchange
        CurrencyExchangeEntity currencyExchange = null;
        double finalAmount = amount;
        BadgeEntity emisorBadge = badgeRepository.findById(emisorBadgeDTO.getId()).orElse(null);
        if (emisorBadge.getId() != finalBadge.getId()) {
            //There exists a currency exchange
            currencyExchange = new CurrencyExchangeEntity();
            currencyExchange.setBadgeByFinalBadgeId(finalBadge);
            currencyExchange.setBadgeByInitialBadgeId(emisorBadge);
            currencyExchange.setInitialAmount(amount);
            finalAmount = amount * emisorBadge.getValue() / finalBadge.getValue();
            currencyExchange.setFinalAmount(finalAmount);

            currencyExchangeRepository.save(currencyExchange);
        }

        //Update the bank account(s)
        BankAccountEntity bankAccountEmisor = bankAccountRepository.findByIban(bankAccountEmisorDTO.getIban()).orElse(null);
        updateBankAccounts(amount, ibanReceptor, finalBadge, finalAmount, emisorBadge, bankAccountEmisor);


        //Make the payment
        PaymentEntity payment = makePayment(amount, beneficiary, currencyExchange);

        //Make the transaction
        makeTransaction(payment, client, bankAccountEmisor);

    }

    private void makeTransaction(PaymentEntity payment, ClientEntity client, BankAccountEntity bankAccount) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setBankAccountByBankAccountId(bankAccount);
        transaction.setClientByClientId(client);
        transaction.setPaymentByPaymentId(payment);
        transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);
    }

    private PaymentEntity makePayment(double amount, BenficiaryEntity beneficiary, CurrencyExchangeEntity currencyExchange) {
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(amount);
        if (currencyExchange != null) {
            payment.setCurrencyExchangeByCurrencyExchangeId(currencyExchange);
        }
        payment.setBenficiaryByBenficiaryId(beneficiary);
        paymentRepository.save(payment);
        return payment;
    }

    private void updateBankAccounts(double amount, String ibanReceptor, BadgeEntity finalBadge, double finalAmount, BadgeEntity emisorBadge, BankAccountEntity bankAccountEmisor) {
        if (!ibanReceptor.equals(bankAccountEmisor.getIban())) {
            //Es una transferencia, se actualiza el dinero de ambas partes
            BankAccountEntity bankAccountReceptor = bankAccountRepository.findByIban(ibanReceptor).orElse(null);
            if (bankAccountReceptor != null) {
                bankAccountReceptor.setBalance(bankAccountReceptor.getBalance() + finalAmount);
                bankAccountRepository.save(bankAccountReceptor);
            }
            bankAccountEmisor.setBalance(bankAccountEmisor.getBalance() - amount);
        } else {
            //Está sacando dinero (ya sea de la misma o distinta moneda), por lo que hay que sacar el dinero final de la cuenta bancaria.
            bankAccountEmisor.setBalance(bankAccountEmisor.getBalance() - (amount * finalBadge.getValue() / emisorBadge.getValue()));
        }
        bankAccountRepository.save(bankAccountEmisor);
    }

    private BenficiaryEntity getOrMakeBeneficiaryEntity(String ibanReceptor, String nameReceptor, BadgeEntity finalBadge) {
        //Find or make the Beneficiary
        BenficiaryEntity beneficiary = beneficiaryRepository.findByIban(ibanReceptor).orElse(null);
        if (beneficiary == null) {
            beneficiary = new BenficiaryEntity();
            beneficiary.setName(nameReceptor);
            beneficiary.setIban(ibanReceptor);
            beneficiary.setBadge(finalBadge.getName());
            beneficiary.setSwift("");

            beneficiaryRepository.save(beneficiary);
        }
        return beneficiary;
    }

}
