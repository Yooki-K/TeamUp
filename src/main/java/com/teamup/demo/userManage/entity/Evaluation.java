package com.teamup.demo.userManage.entity;

public class Evaluation {
    private String user1;
    private String user2;
    private String content;

    public Evaluation(String user1, String user2, String content) {
        this.user1 = user1;
        this.user2 = user2;
        this.content = content;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
