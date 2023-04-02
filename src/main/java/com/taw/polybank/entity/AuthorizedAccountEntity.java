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
    @ManyToOne
    @JoinColumn(name = "Client_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private ClientEntity clientByClientId;
    @ManyToOne
    @JoinColumn(name = "BankAccount_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    private BankAccountEntity bankAccountByBankAccountId;

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

    public byte getBlocked() {
        return blocked;
    }

    public void setBlocked(byte blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizedAccountEntity that = (AuthorizedAccountEntity) o;
        return clientId == that.clientId && bankAccountId == that.bankAccountId && blocked == that.blocked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, bankAccountId, blocked);
    }

    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public BankAccountEntity getBankAccountByBankAccountId() {
        return bankAccountByBankAccountId;
    }

    public void setBankAccountByBankAccountId(BankAccountEntity bankAccountByBankAccountId) {
        this.bankAccountByBankAccountId = bankAccountByBankAccountId;
    }
}
