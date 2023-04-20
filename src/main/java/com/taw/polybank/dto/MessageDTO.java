package com.taw.polybank.dto;

import java.sql.Timestamp;

public class MessageDTO {
    private int id;
    private String content;
    private Timestamp timestamp;
    private ChatDTO chatByChatId;
    private EmployeeDTO employeeByEmployeeId;
    private ClientDTO clientByClientId;

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


    public ChatDTO getChatByChatId() {
        return chatByChatId;
    }

    public void setChatByChatId(ChatDTO chatByChatId) {
        this.chatByChatId = chatByChatId;
    }

    public EmployeeDTO getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeDTO employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    public ClientDTO getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientDTO clientByClientId) {
        this.clientByClientId = clientByClientId;
    }
}
