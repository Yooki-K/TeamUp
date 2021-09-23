package com.teamup.demo.roomManage.entity;

import com.teamup.demo.tool.Util;

import java.util.Date;

public class Invitation {
    private int id;
    private String user1;
    private String user2;
    private int roomId;
    private Date sendTime;
    private boolean isAgree;
    private boolean isStudent;
    private Date recvTime;



    public Invitation(int id, String user1, String user2, int roomId, Date sendTime, boolean isAgree) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.roomId = roomId;
        this.sendTime = sendTime;
        this.isAgree = isAgree;
    }
    public Invitation(){}

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
    public Invitation(String user1, String user2, int roomId, boolean isStudent) {
        this.user1 = user1;
        this.user2 = user2;
        this.roomId = roomId;
        setSendTime();
        this.isStudent = isStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getSendTimeString() {
        return Util.getStringDate(this.sendTime);
    }
    public Date getSendTime() {
        return this.sendTime;
    }

    public void setSendTime( ) {
        this.sendTime = new Date();
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }
}
