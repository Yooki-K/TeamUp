package com.teamup.demo.roomManage.entity;

import com.teamup.demo.tool.Util;

import java.sql.Blob;
import java.util.Date;

public class Room {
    private int id;
    private String user;
    private String name;
    private String content;
    private String tag;
    private Blob background;
    private int targetNum;
    private int curNum;
    private boolean isPwd;
    private String pwd;
    private java.util.Date createTime;

    public Room(int id, String user, String name, String content, String tag, Blob background, int targetNum, int curNum, boolean isPwd, String pwd, Date createTime) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.content = content;
        this.tag = tag;
        this.background = background;
        this.targetNum = targetNum;
        this.curNum = curNum;
        this.isPwd = isPwd;
        this.pwd = pwd;
        this.createTime = createTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Blob getBackground() {
        return background;
    }

    public void setBackground(Blob background) {
        this.background = background;
    }

    public int getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
    }

    public int getCurNum() {
        return curNum;
    }

    public void setCurNum(int curNum) {
        this.curNum = curNum;
    }

    public boolean isPwd() {
        return isPwd;
    }

    public void setPwd(boolean pwd) {
        isPwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCreateTime() {
        return Util.getStringDate(this.createTime);
    }

    public void setCreateTime() {
        this.createTime = new java.util.Date();
    }
}
