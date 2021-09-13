package com.teamup.demo.userManage.entity;

import com.teamup.demo.tool.Util;

import java.util.Date;

public class ApplyRoom {
    private int id;
    private String user;
    private int roomId;
    private String content;
    private boolean isAgree;
    private java.util.Date time;

    public ApplyRoom(int id, String user, int roomId, String content, boolean isAgree, Date time) {
        this.id = id;
        this.user = user;
        this.roomId = roomId;
        this.content = content;
        this.isAgree = isAgree;
        this.time = time;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return Util.getStringDate(this.time);
    }

    public void setTime() {
        time = new Date();
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }
}
