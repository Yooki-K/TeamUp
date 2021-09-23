package com.teamup.demo.entity;

import java.util.Date;

public class MailCode {
    private int id;
    private String user;
    private int sendType;
    private String code;
    private long sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime() {
        this.sendTime = new Date().getTime();
    }

    public MailCode(String user, String code, int sendType) {
        this.user = user;
        this.sendType = sendType;
        this.code = code;
        setSendTime();
    }
    public MailCode(String user, String code) {
        this.user = user;
        setSendType(1);
        this.code = code;
        setSendTime();
    }
}
