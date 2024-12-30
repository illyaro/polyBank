package com.taw.polybank.dto;

import com.taw.polybank.entity.BankAccountEntity;

/**
 * @author José Manuel Sánchez Rico
 */
public class BankAccountDTO {
    private int id;
    private String iban;
    private boolean active;
    private double balance;
    private ClientDTO clientByClientId;
    private BadgeDTO badgeByBadgeId;

    public BankAccountDTO(){}

    public BankAccountDTO(BankAccountEntity account){
        this.id = account.getId();
        this.iban = account.getIban();
        this.active = account.isActive();
        this.balance = account.getBalance();
        this.clientByClientId = new ClientDTO(account.getClientByClientId());
        this.badgeByBadgeId = new BadgeDTO(account.getBadgeByBadgeId());
    }

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


    public ClientDTO getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientDTO clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public BadgeDTO getBadgeByBadgeId() {
        return badgeByBadgeId;
    }

    public void setBadgeByBadgeId(BadgeDTO badgeByBadgeId) {
        this.badgeByBadgeId = badgeByBadgeId;
    }

}
