package com.taw.polybank.entity;

import com.taw.polybank.dto.BankAccountDTO;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "BankAccount", schema = "polyBank", catalog = "")
public class BankAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "IBAN", nullable = false, length = 34)
    private String iban;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    private double balance;
    @OneToMany(mappedBy = "bankAccountByBankAccountId")
    private Collection<AuthorizedAccountEntity> authorizedAccountsById;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByClientId;
    @ManyToOne
    @JoinColumn(name = "Badge_id", referencedColumnName = "id", nullable = false)
    private BadgeEntity badgeByBadgeId;
    @OneToMany(mappedBy = "bankAccountByBankAccountId")
    private List<CompanyEntity> companiesById;
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccountEntity that = (BankAccountEntity) o;

        if (id != that.id) return false;
        if (active != that.active) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (iban != null ? !iban.equals(that.iban) : that.iban != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        result = 31 * result + (active? 1 : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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

    public List<CompanyEntity> getCompanyById() {
        return companiesById;
    }

    public void setCompanyById(List<CompanyEntity> companiesById) {
        this.companiesById = companiesById;
    }
    public List<CompanyEntity> getCompaniesById() {
        return companiesById;
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

    public BankAccountDTO toDTO() {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(getId());
        bankAccountDTO.setIban(getIban());
        bankAccountDTO.setActive(isActive());
        bankAccountDTO.setBalance(getBalance());
        bankAccountDTO.setClientByClientId(getClientByClientId().toDTO());
        bankAccountDTO.setBadgeByBadgeId(getBadgeByBadgeId().toDTO());
        return bankAccountDTO;
    }

    public static BankAccountEntity toEntity(BankAccountDTO bankAccountDTO) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setActive(bankAccountDTO.isActive());
        bankAccountEntity.setId(bankAccountDTO.getId());
        bankAccountEntity.setBalance(bankAccountDTO.getBalance());
        bankAccountEntity.setIban(bankAccountDTO.getIban());
        bankAccountEntity.setClientByClientId(ClientEntity.toEntity(bankAccountDTO.getClientByClientId()));
        return bankAccountEntity;
    }
}
