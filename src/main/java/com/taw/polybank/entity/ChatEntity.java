package com.taw.polybank.entity;

import com.taw.polybank.dto.ChatDTO;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Chat", schema = "polyBank", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "closed", nullable = false)
    private byte closed;
    @ManyToOne
    @JoinColumn(name = "Client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByClientId;
    @ManyToOne
    @JoinColumn(name = "Assistant_id", referencedColumnName = "id", nullable = false)
    private EmployeeEntity employeeByAssistantId;
    @OneToMany(mappedBy = "chatByChatId")
    private Collection<MessageEntity> messagesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        if (id != that.id) return false;
        if (closed != that.closed) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) closed;
        return result;
    }

    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public EmployeeEntity getEmployeeByAssistantId() {
        return employeeByAssistantId;
    }

    public void setEmployeeByAssistantId(EmployeeEntity employeeByAssistantId) {
        this.employeeByAssistantId = employeeByAssistantId;
    }

    public Collection<MessageEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessageEntity> messagesById) {
        this.messagesById = messagesById;
    }

    public ChatDTO toDTO(){
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(getId());
        chatDTO.setClient(getClientByClientId().toDTO());
        chatDTO.setAssistant(getEmployeeByAssistantId().toDTO());
        chatDTO.setClosed(getClosed() != 0);
        return chatDTO;
    }
}
