package com.taw.polybank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class AuthorizedAccountEntityPK implements Serializable {
    @Column(name = "Client_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    @Column(name = "BankAccount_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankAccountId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizedAccountEntityPK that = (AuthorizedAccountEntityPK) o;
        return clientId == that.clientId && bankAccountId == that.bankAccountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, bankAccountId);
    }
}
