package com.teamup.demo.classManage.entity;

public class Class {
    private int id;
    private String user;
    private String userId;

    public Class(int id, String user, String userId) {
        this.id = id;
        this.user = user;
        this.userId = userId;
    }

    public Class(int id, String user) {
        this.id = id;
        this.user = user;
        this.userId=null;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
