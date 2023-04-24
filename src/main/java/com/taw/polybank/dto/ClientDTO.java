package com.taw.polybank.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

public class ClientDTO implements Serializable {
    private int id;
    private String dni;
    private String name;
    private String password;
    private String salt;
    private String surname;
    private Timestamp creationDate;
    private Collection<AuthorizedAccount> authorizedAccountList;
    private Collection<BankAccount> bankAccountList;
    private Collection<ChatDTO> chatList;
    private Collection<MessageDTO> messageList;
    private Collection<Request> requestList;
    private Collection<Transaction> transactionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Collection<AuthorizedAccount> getAuthorizedAccountList() {
        return authorizedAccountList;
    }

    public void setAuthorizedAccountList(Collection<AuthorizedAccount> authorizedAccountList) {
        this.authorizedAccountList = authorizedAccountList;
    }

    public Collection<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(Collection<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    public Collection<ChatDTO> getChatList() {
        return chatList;
    }

    public void setChatList(Collection<ChatDTO> chatList) {
        this.chatList = chatList;
    }

    public Collection<MessageDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList(Collection<MessageDTO> messageList) {
        this.messageList = messageList;
    }

    public Collection<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(Collection<Request> requestList) {
        this.requestList = requestList;
    }

    public Collection<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(Collection<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
