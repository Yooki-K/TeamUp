package com.teamup.demo.tool;

import java.util.Map;

public class Message {
    private boolean isSuccessful;
    private String mes;
    private Map<String,Object> data;
    public Message(boolean isSuccessful, String mes) {
        this.isSuccessful = isSuccessful;
        this.mes = mes;
    }

    public Message(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Message(boolean isSuccessful, String mes, Map<String, Object> data) {
        this.isSuccessful = isSuccessful;
        this.mes = mes;
        this.data = data;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMes() {
        return mes;
    }
}
