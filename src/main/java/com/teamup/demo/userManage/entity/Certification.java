package com.teamup.demo.userManage.entity;

import com.teamup.demo.tool.Util;

import java.util.Date;

public class Certification {
    private String user;
    private boolean sex;
    private String id;
    private String school;
    private String name;
    private Date time;
    private Date operateTime;

    private boolean isAgree;
    private int type;
    public Certification(String user,  boolean sex,String id, String school, String name) {
        this.user = user;
        this.id = id;
        this.sex = sex;
        this.school = school;
        this.name = name;
        setTime();
        setSex(false);
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    public Certification(){}
    @Override
    public String toString() {
        return "Certification{" +
                "user='" + user + '\'' +
                ", id='" + id + '\'' +
                ", sex=" + sex +
                ", school='" + school + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", isAgree=" + isAgree +
                ", type=" + type +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeString() {
        return Util.getStringDate(time);
    }
    public Date getTime() {
        return time;
    }

    public void setTime() {
        this.time = new Date();
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }
}
