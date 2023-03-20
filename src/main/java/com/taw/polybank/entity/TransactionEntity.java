package com.taw.polybank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Transaction", schema = "polyBank", catalog = "")
public class TransactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Basic
    @Column(name = "Client_id")
    private int clientId;
    @Basic
    @Column(name = "BankAccount_id")
    private int bankAccountId;
    @Basic
    @Column(name = "CurrencyExchange_id")
    private int currencyExchangeId;
    @Basic
    @Column(name = "Payment_id")
    private int paymentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public int getCurrencyExchangeId() {
        return currencyExchangeId;
    }

    public void setCurrencyExchangeId(int currencyExchangeId) {
        this.currencyExchangeId = currencyExchangeId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id == that.id && clientId == that.clientId && bankAccountId == that.bankAccountId && currencyExchangeId == that.currencyExchangeId && paymentId == that.paymentId && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, clientId, bankAccountId, currencyExchangeId, paymentId);
    }
}
