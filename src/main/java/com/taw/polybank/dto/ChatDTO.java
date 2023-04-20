package com.taw.polybank.dto;

public class ChatDTO {
    private int id;
    private boolean closed;
    private ClientDTO clientByClientId;
    private EmployeeDTO employeeByAssistantId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }


    public ClientDTO getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientDTO clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public EmployeeDTO getEmployeeByAssistantId() {
        return employeeByAssistantId;
    }

    public void setEmployeeByAssistantId(EmployeeDTO employeeByAssistantId) {
        this.employeeByAssistantId = employeeByAssistantId;
    }

}
