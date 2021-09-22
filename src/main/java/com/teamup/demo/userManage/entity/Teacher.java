package com.teamup.demo.userManage.entity;


import com.teamup.demo.tool.Util;

import java.util.Base64;

public class Teacher extends Admin{
    String headshot;
    String school;
    String id;
    String name;
    boolean sex;
    String collage;
    String mail;
    int No;

    @Override
    public String toString() {
        return "Teacher{" +
                "user='" + user + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

}
