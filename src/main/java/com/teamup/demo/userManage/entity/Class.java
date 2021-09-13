package com.teamup.demo.userManage.entity;

public class Class {
    private int id;
    private String user;
    private String invCode;

    public Class(int id, String user, String invCode) {
        this.id = id;
        this.user = user;
        this.invCode = invCode;
    }

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

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }
}
