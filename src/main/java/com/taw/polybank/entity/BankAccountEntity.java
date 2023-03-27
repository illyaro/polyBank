package com.taw.polybank.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "BankAccount", schema = "polyBank", catalog = "")
public class BankAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "client_id")
    private int clientId;
    @Basic
    @Column(name = "IBAN")
    private String iban;
    @Basic
    @Column(name = "active")
    private byte active;
    @Basic
    @Column(name = "balance")
    private double balance;
    @Basic
    @Column(name = "Badge_id")
    private int badgeId;
    @OneToMany(mappedBy = "bankAccountByBankAccountId")
    private Collection<AuthorizedAccountEntity> authorizedAccountsById;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByClientId;
    @ManyToOne
    @JoinColumn(name = "Badge_id", referencedColumnName = "id", nullable = false)
    private BadgeEntity badgeByBadgeId;
    @OneToMany(mappedBy = "bankAccountByBankAccountId")
    private Collection<CompanyEntity> companiesById;
    @OneToMany(mappedBy = "bankAccountByBankAccountId")
    private Collection<RequestEntity> requestsById;
    @OneToMany(mappedBy = "bankAccountByBankAccountId")
    private Collection<TransactionEntity> transactionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return id == that.id && clientId == that.clientId && active == that.active && balance == that.balance && badgeId == that.badgeId && Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, iban, active, balance, badgeId);
    }

    public Collection<AuthorizedAccountEntity> getAuthorizedAccountsById() {
        return authorizedAccountsById;
    }

    public void setAuthorizedAccountsById(Collection<AuthorizedAccountEntity> authorizedAccountsById) {
        this.authorizedAccountsById = authorizedAccountsById;
    }

    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public BadgeEntity getBadgeByBadgeId() {
        return badgeByBadgeId;
    }

    public void setBadgeByBadgeId(BadgeEntity badgeByBadgeId) {
        this.badgeByBadgeId = badgeByBadgeId;
    }

    public Collection<CompanyEntity> getCompaniesById() {
        return companiesById;
    }

    public void setCompaniesById(Collection<CompanyEntity> companiesById) {
        this.companiesById = companiesById;
    }

    public Collection<RequestEntity> getRequestsById() {
        return requestsById;
    }

    public void setRequestsById(Collection<RequestEntity> requestsById) {
        this.requestsById = requestsById;
    }

    public Collection<TransactionEntity> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<TransactionEntity> transactionsById) {
        this.transactionsById = transactionsById;
    }
}
