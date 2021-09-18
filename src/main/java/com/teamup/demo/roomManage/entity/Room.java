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
    private String teamName;
    private int targetNum;
    private int curNum;
    private String pwd;
    private java.util.Date createTime;
    private int classId;
    private String color;
    private int teacherId;

    public Room(int id, String user, String name, String content, String tag, String teamName, int targetNum, int curNum, String pwd, int classId, String color, int teacherId) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.content = content;
        this.tag = tag;
        this.teamName = teamName;
        this.targetNum = targetNum;
        this.curNum = curNum;
        this.pwd = pwd;
        this.classId = classId;
        this.color = color;
        this.teacherId = teacherId;
        setCreateTime();
    }

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", teamName='" + teamName + '\'' +
                ", targetNum=" + targetNum +
                ", curNum=" + curNum +
                ", pwd='" + pwd + '\'' +
                ", createTime=" + createTime +
                ", classId=" + classId +
                ", color='" + color + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Room(int id, String user, String name, String content, String tag, String teamName, int targetNum, int curNum, String pwd, Date createTime, int classId, String color, int teacherId) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.content = content;
        this.tag = tag;
        this.teamName = teamName;
        this.targetNum = targetNum;
        this.curNum = curNum;
        this.pwd = pwd;
        this.createTime = createTime;
        this.classId = classId;
        this.color = color;
        this.teacherId = teacherId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
