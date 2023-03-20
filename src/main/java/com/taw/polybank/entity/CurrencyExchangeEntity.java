package com.taw.polybank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CurrencyExchange", schema = "polyBank", catalog = "")
public class CurrencyExchangeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "initialAmount")
    private double initialAmount;
    @Basic
    @Column(name = "finalAmount")
    private double finalAmount;
    @Basic
    @Column(name = "initialBadge_id")
    private int initialBadgeId;
    @Basic
    @Column(name = "finalBadge_id")
    private int finalBadgeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public int getInitialBadgeId() {
        return initialBadgeId;
    }

    public void setInitialBadgeId(int initialBadgeId) {
        this.initialBadgeId = initialBadgeId;
    }

    public int getFinalBadgeId() {
        return finalBadgeId;
    }

    public void setFinalBadgeId(int finalBadgeId) {
        this.finalBadgeId = finalBadgeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyExchangeEntity that = (CurrencyExchangeEntity) o;
        return id == that.id && Double.compare(that.initialAmount, initialAmount) == 0 && Double.compare(that.finalAmount, finalAmount) == 0 && initialBadgeId == that.initialBadgeId && finalBadgeId == that.finalBadgeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initialAmount, finalAmount, initialBadgeId, finalBadgeId);
    }
}
