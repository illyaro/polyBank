package com.taw.polybank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "AuthorizedAccount", schema = "polyBank", catalog = "")
@IdClass(AuthorizedAccountEntityPK.class)
public class AuthorizedAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Client_id")
    private int clientId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "BankAccount_id")
    private int bankAccountId;
    @Basic
    @Column(name = "blocked")
    private byte blocked;

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
        AuthorizedAccountEntity that = (AuthorizedAccountEntity) o;
        return clientId == that.clientId && bankAccountId == that.bankAccountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, bankAccountId);
    }

    public byte getBlocked() {
        return blocked;
    }

    public void setBlocked(byte blocked) {
        this.blocked = blocked;
    }
}
