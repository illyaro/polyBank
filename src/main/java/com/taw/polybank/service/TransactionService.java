package com.taw.polybank.service;

import com.taw.polybank.dao.TransactionRepository;
import com.taw.polybank.dto.*;
import com.taw.polybank.entity.CurrencyExchangeEntity;
import com.taw.polybank.entity.TransactionEntity;
import com.taw.polybank.ui.transaction.TransactionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void save(TransactionDTO transactionDTO,
            ClientService clientService, BankAccountService bankAccountService,
            CurrencyExchangeService currencyExchangeService, PaymentService paymentService,
            BadgeService badgeService, BeneficiaryService beneficiaryService) {
        TransactionEntity transaction = this.toEntity(transactionDTO, clientService, bankAccountService,
                currencyExchangeService, paymentService, badgeService, beneficiaryService);
        transactionRepository.save(transaction);
        transactionDTO.setId(transaction.getId());
    }

    private TransactionEntity toEntity(TransactionDTO transactionDTO,
            ClientService clientService, BankAccountService bankAccountService,
            CurrencyExchangeService currencyExchangeService, PaymentService paymentService,
            BadgeService badgeService, BeneficiaryService beneficiaryService) {
        TransactionEntity transaction = transactionRepository.findById(transactionDTO.getId())
                .orElse(new TransactionEntity());
        transaction.setId(transactionDTO.getId());
        transaction.setTimestamp(transactionDTO.getTimestamp());
        transaction.setClientByClientId(clientService.toEntidy(transactionDTO.getClientByClientId()));
        transaction.setBankAccountByBankAccountId(bankAccountService
                .toEntity(transactionDTO.getBankAccountByBankAccountId(), clientService, badgeService));
        CurrencyExchangeEntity currencyExchangeEntity = transactionDTO.getCurrencyExchangeByCurrencyExchangeId() == null
                ? null
                : currencyExchangeService.toEntity(transactionDTO.getCurrencyExchangeByCurrencyExchangeId(),
                        badgeService);
        transaction.setCurrencyExchangeByCurrencyExchangeId(currencyExchangeEntity);
        transaction.setPaymentByPaymentId(paymentService.toEntity(transactionDTO.getPaymentByPaymentId(),
                beneficiaryService, currencyExchangeService, badgeService));
        return transaction;
    }

    public List<TransactionDTO> findTransactionsByBankAccountByBankAccountIdId(int bankId) {
        return transactionRepository.findTransactionEntitiesByBankAccountByBankAccountIdId(bankId)
                .stream()
                .map(transaction -> transaction.toDTO())
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> findAllTransactionsByBankAccountAndDatesAndSendAmountInRange(int bankId,
            Timestamp dateAfter, Timestamp dateBefore, double minAmount, double maxAmount) {
        return transactionRepository.findAllTransactionsByBankAccountAndDatesAndSendAmountInRange(
                bankId, dateAfter, dateBefore, minAmount, maxAmount)
                .stream()
                .map(transaction -> transaction.toDTO())
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> findAllTransactionsByBankAccountAndDatesAndSendAmountInRangeWithGivenSenderDni(
            int bankId, Timestamp dateAfter, Timestamp dateBefore, double minAmount, double maxAmount,
            String senderId) {
        return transactionRepository.findAllTransactionsByBankAccountAndDatesAndSendAmountInRangeWithGivenSenderDni(
                bankId, dateAfter, dateBefore, minAmount, maxAmount, senderId)
                .stream()
                .map(transaction -> transaction.toDTO())
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> findAllTransactionsByBankAccountAndDatesAndSendAmountInRangeWithGivenRecipientName(
            int bankId, Timestamp dateAfter, Timestamp dateBefore, double minAmount, double maxAmount,
            String recipientName) {
        return transactionRepository.findAllTransactionsByBankAccountAndDatesAndSendAmountInRangeWithGivenRecipientName(
                bankId, dateAfter, dateBefore, minAmount, maxAmount, recipientName)
                .stream()
                .map(transaction -> transaction.toDTO())
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> findAllTransactionsByBankAccountAndDatesAndSendAmountInRangeWithGivenSenderDniAndRecipientName(
            int bankId, Timestamp dateAfter, Timestamp dateBefore, double minAmount, double maxAmount, String senderId,
            String recipientName) {
        return transactionRepository
                .findAllTransactionsByBankAccountAndDatesAndSendAmountInRangeWithGivenSenderDniAndRecipientName(
                        bankId, dateAfter, dateBefore, minAmount, maxAmount, senderId, recipientName)
                .stream()
                .map(transaction -> transaction.toDTO())
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> findByClientIdAndFilter(Integer id, TransactionFilter filter) {
        if (filter.getType() == null || filter.getType().isBlank())
            return findByClientIdAndMinimumAmount(id, filter.getAmount());
        if (filter.getType().equals("Payment")) {
            return getPaymentByClientIdDTOS(id, filter);
        } else if (filter.getType().equals("CurrencyExchange")) {
            return getCurrencyExchangeByClientIdDTOS(id, filter);
        } else {
            return getAnyTransactionByClientIdDTOS(id, filter);
        }
    }

    public List<TransactionDTO> findByBankIdAndFilter(int id, TransactionFilter filter) {
        if (filter.getType() == null || filter.getType().isBlank())
            return getDtoList(transactionRepository.findByBankIdAndMinimumAmount(id, filter.getAmount()));
        if (filter.getType().equals("Payment")) {
            return getPaymentByBankIdDTOS(id, filter);
        } else if (filter.getType().equals("CurrencyExchange")) {
            return getCurrencyExchangeByBankIdDTOS(id, filter);
        } else
            return getAnyTransactionByClientIdDTOS(id, filter);
    }

    private List<TransactionDTO> getAnyTransactionByClientIdDTOS(Integer id, TransactionFilter filter) {
        if (filter.getSorting() == null || filter.getSorting().isBlank()) {
            return findByClientId(id);
        } else if (filter.getSorting().equals("timestamp_asc")) {
            return findByClientIdSortByTimestampAsc(id);
        } else if (filter.getSorting().equals("timestamp_desc")) {
            return findByClientIdSortByTimestampDesc(id);
        } else
            return findByClientId(id);
    }

    private List<TransactionDTO> getCurrencyExchangeByClientIdDTOS(Integer id, TransactionFilter filter) {
        if (filter.getSorting() == null || filter.getSorting().isBlank()) {
            return findCurrencyExchangesByClientIdAndMinimumAmount(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_asc")) {
            return findCurrencyExchangesByClientIdAndMinimumAmountSortByTimestampAsc(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_desc")) {
            return findCurrencyExchangesByClientIdAndMinimumAmountSortByTimestampDesc(id, filter.getAmount());
        } else
            return findCurrencyExchangesByClientIdAndMinimumAmount(id, filter.getAmount());
    }

    private List<TransactionDTO> getPaymentByClientIdDTOS(Integer id, TransactionFilter filter) {
        if (filter.getSorting() == null || filter.getSorting().isBlank()) {
            return findPaymentsByClientIdAndMinimumAmount(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_asc")) {
            return findPaymentsByClientIdAndMinimumAmountSortByTimestampAsc(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_desc")) {
            return findPaymentsByClientIdAndMinimumAmountSortByTimestampDesc(id, filter.getAmount());
        } else
            return findPaymentsByClientIdAndMinimumAmount(id, filter.getAmount());
    }

    private List<TransactionDTO> getAnyTransactionByBankIdDTOS(Integer id, TransactionFilter filter) {
        if (filter.getSorting() == null || filter.getSorting().isBlank()) {
            return findByBankId(id);
        } else if (filter.getSorting().equals("timestamp_asc")) {
            return findByBankIdSortByTimestampAsc(id);
        } else if (filter.getSorting().equals("timestamp_desc")) {
            return findByBankIdSortByTimestampDesc(id);
        } else
            return findByBankId(id);
    }

    private List<TransactionDTO> findByBankIdSortByTimestampDesc(Integer id) {
        return getDtoList(transactionRepository.findByBankIdSortByTimestampDesc(id));
    }

    private List<TransactionDTO> findByBankIdSortByTimestampAsc(Integer id) {
        return getDtoList(transactionRepository.findByBankIdSortByTimestampAsc(id));
    }

    private List<TransactionDTO> getCurrencyExchangeByBankIdDTOS(Integer id, TransactionFilter filter) {
        if (filter.getSorting() == null || filter.getSorting().isBlank()) {
            return findCurrencyExchangesByBankIdAndMinimumAmount(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_asc")) {
            return findCurrencyExchangesByBankIdAndMinimumAmountSortByTimestampAsc(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_desc")) {
            return findCurrencyExchangesByBankIdAndMinimumAmountSortByTimestampDesc(id, filter.getAmount());
        } else
            return findCurrencyExchangesByBankIdAndMinimumAmount(id, filter.getAmount());
    }

    private List<TransactionDTO> findCurrencyExchangesByBankIdAndMinimumAmountSortByTimestampAsc(Integer id,
            double amount) {
        return getDtoList(
                transactionRepository.findCurrencyExchangesByBankIdAndMinimumAmountSortByTimestampAsc(id, amount));
    }

    private List<TransactionDTO> findCurrencyExchangesByBankIdAndMinimumAmountSortByTimestampDesc(Integer id,
            double amount) {
        return getDtoList(
                transactionRepository.findCurrencyExchangesByBankIdAndMinimumAmountSortByTimestampDesc(id, amount));
    }

    private List<TransactionDTO> findCurrencyExchangesByBankIdAndMinimumAmount(Integer id, double amount) {
        return getDtoList(transactionRepository.findCurrencyExchangesByBankIdAndMinimumAmount(id, amount));
    }

    private List<TransactionDTO> getPaymentByBankIdDTOS(Integer id, TransactionFilter filter) {
        if (filter.getSorting() == null || filter.getSorting().isBlank()) {
            return findPaymentsByBankIdAndMinimumAmount(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_asc")) {
            return findPaymentsByBankIdAndMinimumAmountSortByTimestampAsc(id, filter.getAmount());
        } else if (filter.getSorting().equals("timestamp_desc")) {
            return findPaymentsByBankIdAndMinimumAmountSortByTimestampDesc(id, filter.getAmount());
        } else
            return findPaymentsByBankIdAndMinimumAmount(id, filter.getAmount());
    }

    private List<TransactionDTO> findPaymentsByBankIdAndMinimumAmountSortByTimestampAsc(Integer id, double amount) {
        return getDtoList(transactionRepository.findPaymentsByBankIdAndMinimumAmountSortByTimestampAsc(id, amount));
    }

    private List<TransactionDTO> findPaymentsByBankIdAndMinimumAmountSortByTimestampDesc(Integer id, double amount) {
        return getDtoList(transactionRepository.findPaymentsByBankIdAndMinimumAmountSortByTimestampDesc(id, amount));
    }

    private List<TransactionDTO> findPaymentsByBankIdAndMinimumAmount(Integer id, double amount) {
        return getDtoList(transactionRepository.findPaymentsByBankIdAndMinimumAmount(id, amount));
    }

    private List<TransactionDTO> findByClientIdSortByTimestampDesc(Integer id) {
        return getDtoList(transactionRepository.findByClientIdSortByTimestampDesc(id));
    }

    private List<TransactionDTO> findByClientIdSortByTimestampAsc(Integer id) {
        return getDtoList(transactionRepository.findByClientIdSortByTimestampAsc(id));
    }

    private List<TransactionDTO> findCurrencyExchangesByClientIdAndMinimumAmountSortByTimestampDesc(Integer id,
            double amount) {
        return getDtoList(
                transactionRepository.findCurrencyExchangesByClientIdAndMinimumAmountSortByTimestampDesc(id, amount));
    }

    private List<TransactionDTO> findCurrencyExchangesByClientIdAndMinimumAmountSortByTimestampAsc(Integer id,
            double amount) {
        return getDtoList(
                transactionRepository.findCurrencyExchangesByClientIdAndMinimumAmountSortByTimestampAsc(id, amount));
    }

    private List<TransactionDTO> findPaymentsByClientIdAndMinimumAmountSortByTimestampAsc(Integer id, double amount) {
        return getDtoList(transactionRepository.findPaymentsByClientIdAndMinimumAmountSortByTimestampAsc(id, amount));
    }

    public List<TransactionDTO> findByClientId(int id) {
        return getDtoList(transactionRepository.findByClientId(id));
    }

    private List<TransactionDTO> findCurrencyExchangesByClientIdAndMinimumAmount(Integer id, double amount) {
        return getDtoList(transactionRepository.findCurrencyExchangesByClientIdAndMinimumAmount(id, amount));
    }

    private List<TransactionDTO> findPaymentsByClientIdAndMinimumAmount(Integer id, double amount) {
        return getDtoList(transactionRepository.findPaymentsByClientIdAndMinimumAmount(id, amount));

    }

    public List<TransactionDTO> findByClientIdAndMinimumAmount(Integer id, double amount) {
        return getDtoList(transactionRepository.findByClientIdAndMinimumAmount(id, amount));
    }

    private List<TransactionDTO> getDtoList(List<TransactionEntity> transactionEntityList) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntityList) {
            transactionDTOS.add(transactionEntity.toDTO());
        }
        return transactionDTOS;
    }

    private List<TransactionDTO> findPaymentsByClientIdAndMinimumAmountSortByTimestampDesc(int id, double amount) {
        return getDtoList(transactionRepository.findPaymentsByClientIdAndMinimumAmountSortByTimestampDesc(id, amount));
    }

    public List<TransactionDTO> findByBankId(int id) {
        return getDtoList(transactionRepository.findByBankAccountId(id));
    }
}
