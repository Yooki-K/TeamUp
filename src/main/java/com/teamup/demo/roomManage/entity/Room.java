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
    private int targetNum;
    private int curNum;
    private boolean isPwd;
    private String pwd;
    private java.util.Date createTime;
    private int classId;
    private int teacherId;
    private String teamName;
    private String color;

    public Room( int id, String user, String name, int targetNum, int curNum,boolean isPwd, Date createTime){
        this.id = id;
        this.user = user;
        this.name = name;
        this.targetNum = targetNum;
        this.curNum = curNum;
        this.isPwd = isPwd;
        this.createTime = createTime;
    }

    public Room(int id, String user, String name, String content, String tag, int targetNum, int curNum, boolean isPwd, String pwd, Date createTime, int classId, int teacherId, String teamName, String color) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.content = content;
        this.tag = tag;
        this.targetNum = targetNum;
        this.curNum = curNum;
        this.isPwd = isPwd;
        this.pwd = pwd;
        this.createTime = createTime;
        this.classId = classId;
        this.teacherId = teacherId;
        this.teamName = teamName;
        this.color = color;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
