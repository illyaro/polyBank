package com.taw.polybank.dao;

import com.taw.polybank.entity.BankAccountEntity;
import com.taw.polybank.entity.BenficiaryEntity;
import com.taw.polybank.entity.ClientEntity;
import com.taw.polybank.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    public List<TransactionEntity> findByBankAccountByBankAccountId(BankAccountEntity bankAccount);

    @Query("select t from TransactionEntity t where t.bankAccountByBankAccountId = :bankAccount and t.timestamp >= :begin and t.timestamp <= :end and t.clientByClientId in :clients and t.paymentByPaymentId.benficiaryByBenficiaryId = :beneficiary and t.paymentByPaymentId.amount >= :amount")
    List<TransactionEntity> filterByBankAccount_TimestampRange_TransactionOwner_beneficiaryIBAN_Amount(BankAccountEntity bankAccount, Timestamp begin, Timestamp end, List<ClientEntity> clients, BenficiaryEntity beneficiary, int amount);

    @Query("select t from TransactionEntity t where t.bankAccountByBankAccountId = :bankAccount and t.timestamp >= :begin and t.timestamp <= :end and t.paymentByPaymentId.benficiaryByBenficiaryId = :beneficiary and t.paymentByPaymentId.amount >= :amount")
    List<TransactionEntity> filterByBankAccount_TimestampRange_beneficiaryIBAN_Amount(BankAccountEntity bankAccount, Timestamp begin, Timestamp end, BenficiaryEntity beneficiary, int amount);

    @Query("select t from TransactionEntity t where t.bankAccountByBankAccountId = :bankAccount and t.timestamp >= :begin and t.timestamp <= :end and t.clientByClientId in :clients and t.paymentByPaymentId.amount >= :amount")
    List<TransactionEntity> filterByBankAccount_TimestampRange_TransactionOwner_Amount(BankAccountEntity bankAccount, Timestamp begin, Timestamp end, List<ClientEntity> clients, int amount);

    @Query("select t from TransactionEntity t where t.bankAccountByBankAccountId = :bankAccount and t.timestamp >= :begin and t.timestamp <= :end and t.paymentByPaymentId.amount >= :amount")
    List<TransactionEntity> filterByBankAccount_TimestampRange_Amount(BankAccountEntity bankAccount, Timestamp begin, Timestamp end, int amount);


}
