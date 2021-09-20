package com.teamup.demo.entity;

public class Message {
    private boolean isSuccessful;
    private String mes;

    public Message(boolean isSuccessful, String mes) {
        this.isSuccessful = isSuccessful;
        this.mes = mes;
    }

    public Message(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMes() {
        return mes;
    }
}
