package com.taw.polybank.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Payment", schema = "polyBank", catalog = "")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "Benficiary_id")
    private int benficiaryId;
    @Basic
    @Column(name = "CurrencyExchange_id")
    private Integer currencyExchangeId;
    @Basic
    @Column(name = "amount")
    private double amount;
    @ManyToOne
    @JoinColumn(name = "Benficiary_id", referencedColumnName = "id", nullable = false)
    private BenficiaryEntity benficiaryByBenficiaryId;
    @ManyToOne
    @JoinColumn(name = "CurrencyExchange_id", referencedColumnName = "id")
    private CurrencyExchangeEntity currencyExchangeByCurrencyExchangeId;
    @OneToMany(mappedBy = "paymentByPaymentId")
    private Collection<TransactionEntity> transactionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBenficiaryId() {
        return benficiaryId;
    }

    public void setBenficiaryId(int benficiaryId) {
        this.benficiaryId = benficiaryId;
    }

    public Integer getCurrencyExchangeId() {
        return currencyExchangeId;
    }

    public void setCurrencyExchangeId(Integer currencyExchangeId) {
        this.currencyExchangeId = currencyExchangeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return id == that.id && benficiaryId == that.benficiaryId && Double.compare(that.amount, amount) == 0 && Objects.equals(currencyExchangeId, that.currencyExchangeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, benficiaryId, currencyExchangeId, amount);
    }

    public BenficiaryEntity getBenficiaryByBenficiaryId() {
        return benficiaryByBenficiaryId;
    }

    public void setBenficiaryByBenficiaryId(BenficiaryEntity benficiaryByBenficiaryId) {
        this.benficiaryByBenficiaryId = benficiaryByBenficiaryId;
    }

    public CurrencyExchangeEntity getCurrencyExchangeByCurrencyExchangeId() {
        return currencyExchangeByCurrencyExchangeId;
    }

    public void setCurrencyExchangeByCurrencyExchangeId(CurrencyExchangeEntity currencyExchangeByCurrencyExchangeId) {
        this.currencyExchangeByCurrencyExchangeId = currencyExchangeByCurrencyExchangeId;
    }

    public Collection<TransactionEntity> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<TransactionEntity> transactionsById) {
        this.transactionsById = transactionsById;
    }
}
