package com.taw.polybank.dto;

import java.io.Serializable;
import java.util.Collection;

public class ChatDTO implements Serializable {
    private int id;
    private byte closed;
    private ClientDTO client;
    private EmployeeDTO assistant;
    private Collection<MessageDTO> messageList;

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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public EmployeeDTO getAssistant() {
        return assistant;
    }

    public void setAssistant(EmployeeDTO assistant) {
        this.assistant = assistant;
    }

    public Collection<MessageDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList(Collection<MessageDTO> messageList) {
        this.messageList = messageList;
    }

    public String isClosed() {
        return (this.getClosed() == 1)? "Yes" : "No";
    }
}
