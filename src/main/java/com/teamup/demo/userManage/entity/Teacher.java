package com.teamup.demo.userManage.entity;

import java.sql.Blob;

public class Teacher extends Admin{
    Blob blob;
    String school;
    String id;
    String name;
    boolean sex;
    String collage;

    @Override
    public String toString() {
        return "Teacher{" +
                "user='" + user + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }
}
