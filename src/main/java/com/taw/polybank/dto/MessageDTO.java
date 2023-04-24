package com.taw.polybank.dto;

import java.sql.Timestamp;

public class MessageDTO {
    private int id;
    private String content;
    private Timestamp timestamp;
    private ChatDTO chat;
    private EmployeeDTO assistant;
    private ClientDTO client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    public ChatDTO getChat() {
        return chat;
    }

    public void setChat(ChatDTO chat) {
        this.chat = chat;
    }

    public EmployeeDTO getAssistant() {
        return assistant;
    }

    public void setAssistant(EmployeeDTO assistant) {
        this.assistant = assistant;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO clientByClientId) {
        this.client = client;
    }
}
