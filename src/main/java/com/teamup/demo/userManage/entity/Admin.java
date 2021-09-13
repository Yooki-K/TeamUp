package com.teamup.demo.userManage.entity;

public class Admin {
    String user;
    String pwd;
    String mail;

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                ", mail='" + mail + '\'' +
                '}';
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Admin(String user, String pwd, String mail) {
        this.user = user;
        this.pwd = pwd;
        this.mail = mail;
    }
}
