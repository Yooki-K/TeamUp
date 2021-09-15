package com.teamup.demo.tool;

import com.teamup.demo.userManage.entity.Certification;

import java.util.List;
import java.util.Map;

public class Message {
     boolean isSuccessful;
     String mes;
    private Map<String, Object> data;

    public Message(boolean isSuccessful, String mes) {
        this.isSuccessful = isSuccessful;
        this.mes = mes;
    }
    public Message(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
    public Message(boolean isSuccessful, String mes, Map<String, Object> data) {
        this.isSuccessful = isSuccessful;
        this.mes = mes;
        this.data = data;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMes() {
        return mes;
    }
}
