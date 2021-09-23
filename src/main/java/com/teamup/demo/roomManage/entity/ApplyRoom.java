package com.teamup.demo.roomManage.entity;

import com.teamup.demo.tool.Util;

import java.util.Date;

public class ApplyRoom {
    private int id;
    private String user;
    private int roomId;
    private String content;
    private boolean isAgree;
    private Date time;

    public ApplyRoom(String user, int roomId, String content) {
        this.user = user;
        this.roomId = roomId;
        this.content = content;
    }
    public ApplyRoom(){}
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

    public String getTimeString() {
        return Util.getStringDate(this.time);
    }
    public Date getTime() {
        return this.time;
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
