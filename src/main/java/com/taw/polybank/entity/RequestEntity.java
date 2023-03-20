package com.taw.polybank.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Request", schema = "polyBank", catalog = "")
public class RequestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "Client_id")
    private int clientId;
    @Basic
    @Column(name = "BankAccount_id")
    private int bankAccountId;
    @Basic
    @Column(name = "Employee_id")
    private int employeeId;
    @Basic
    @Column(name = "solved")
    private byte solved;
    @Basic
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Basic
    @Column(name = "type")
    private Object type;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "approved")
    private Byte approved;

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

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public byte getSolved() {
        return solved;
    }

    public void setSolved(byte solved) {
        this.solved = solved;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getApproved() {
        return approved;
    }

    public void setApproved(Byte approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestEntity that = (RequestEntity) o;
        return id == that.id && clientId == that.clientId && bankAccountId == that.bankAccountId && employeeId == that.employeeId && solved == that.solved && Objects.equals(timestamp, that.timestamp) && Objects.equals(type, that.type) && Objects.equals(description, that.description) && Objects.equals(approved, that.approved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, bankAccountId, employeeId, solved, timestamp, type, description, approved);
    }
}
