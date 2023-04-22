package com.taw.polybank.dto;

import java.io.Serializable;
import java.util.Collection;

public class Chat implements Serializable {
    private int id;
    private byte closed;
    private Client client;
    private Employee assistant;
    private Collection<Message> messageList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getClosed() {
        return closed;
    }

    public void setClosed(byte closed) {
        this.closed = closed;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getAssistant() {
        return assistant;
    }

    public void setAssistant(Employee assistant) {
        this.assistant = assistant;
    }

    public Collection<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(Collection<Message> messageList) {
        this.messageList = messageList;
    }

    public String isClosed() {
        return (this.getClosed() == 1)? "Yes" : "No";
    }
}
