package com.teamup.demo.ClassManage.entity;

public class ClassInfo {
    private int id;
    private String user;
    private String userId;

    public ClassInfo(int id, String user, String userId) {
        this.id = id;
        this.user = user;
        this.userId = userId;
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
