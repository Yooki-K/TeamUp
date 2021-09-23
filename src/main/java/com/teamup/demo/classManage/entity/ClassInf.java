package com.teamup.demo.classManage.entity;

public class ClassInf {
    private int id;
    private String user;
    private String userId;

    public ClassInf(int id, String user, String userId) {
        this.id = id;
        this.user = user;
        this.userId = userId;
    }
    public ClassInf(){}
    @Override
    public String toString() {
        return "ClassInf{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", userId='" + userId + '\'' +
                '}';
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
