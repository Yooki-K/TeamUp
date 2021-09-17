package com.teamup.demo.chatManage.entity;

import com.teamup.demo.tool.Util;

import java.util.Date;

public class ChatLog {
    private int id;
    private String user;
    private String content;
    private java.util.Date time;
    private int teamId;
    private int roomId;

    public ChatLog( int id, String user, String content,  Date time ) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.time = time;
    }

    public ChatLog(int id, String user, String content, Date time, int teamId, int roomId) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.time = time;
        this.teamId = teamId;
        this.roomId = roomId;
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
        this.time = new Date();
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
