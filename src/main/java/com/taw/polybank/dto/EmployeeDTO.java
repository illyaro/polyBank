package com.taw.polybank.dto;

import java.io.Serializable;
import java.util.Collection;

public class EmployeeDTO implements Serializable {
    private int id;
    private String dni;
    private String name;
    private String password;
    private Object type;
    private String salt;
    private Collection<ChatDTO> chatList;
    private Collection<MessageDTO> messageList;
    private Collection<Request> requestList;

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

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
}
