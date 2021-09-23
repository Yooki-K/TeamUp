package com.teamup.demo.classManage.entity;

import com.teamup.demo.tool.Util;

import java.util.Date;

public class Class {
    private int id;
    private String user;
    private String invCode;
    private String name;
    private String announcement;
    private Date publishTime;


    public Class(String name) {
        this.name = name;
    }
    public Class(){}

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", invCode='" + invCode + '\'' +
                ", name='" + name + '\'' +
                ", announcement='" + announcement + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Date getPublishTime() {
        return publishTime;
    }
    public String getPublishTimeString() {
        return Util.getStringDate(publishTime);
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
