package com.taw.polybank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Client", schema = "polyBank", catalog = "")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "surname")
    private String surname;
    @CreationTimestamp
    @Column(name = "creationDate")
    private Timestamp creationDate;
    @Basic
    @Column(name = "DNI")
    private String dni;
    @Basic
    @Column(name = "salt")
    private String salt;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<AuthorizedAccountEntity> authorizedAccountsById;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<BankAccountEntity> bankAccountsById;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<ChatEntity> chatsById;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<MessageEntity> messagesById;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<RequestEntity> requestsById;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<TransactionEntity> transactionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(surname, that.surname) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, surname, creationDate);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Collection<AuthorizedAccountEntity> getAuthorizedAccountsById() {
        return authorizedAccountsById;
    }

    public void setAuthorizedAccountsById(Collection<AuthorizedAccountEntity> authorizedAccountsById) {
        this.authorizedAccountsById = authorizedAccountsById;
    }

    public Collection<BankAccountEntity> getBankAccountsById() {
        return bankAccountsById;
    }

    public void setBankAccountsById(Collection<BankAccountEntity> bankAccountsById) {
        this.bankAccountsById = bankAccountsById;
    }

    public Collection<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(Collection<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public Collection<MessageEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessageEntity> messagesById) {
        this.messagesById = messagesById;
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
