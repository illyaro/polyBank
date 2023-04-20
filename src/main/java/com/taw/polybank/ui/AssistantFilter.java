package com.taw.polybank.ui;

public class AssistantFilter {
    private String dni;
    private String client;
    private Boolean recent;

    public AssistantFilter() {
        this.dni = "";
        this.client = "";
        this.recent = false;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Boolean getRecent() {
        return recent;
    }

    public void setRecent(Boolean recent) {
        this.recent = recent;
    }
}
