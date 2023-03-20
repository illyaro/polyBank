package com.taw.polybank.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Chat", schema = "polyBank", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "Client_id")
    private int clientId;
    @Basic
    @Column(name = "Assistant_id")
    private int assistantId;
    @Basic
    @Column(name = "closed")
    private byte closed;

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

    public int getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
    }

    public byte getClosed() {
        return closed;
    }

    public void setClosed(byte closed) {
        this.closed = closed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return id == that.id && clientId == that.clientId && assistantId == that.assistantId && closed == that.closed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, assistantId, closed);
    }
}
