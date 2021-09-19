package com.teamup.demo.userManage.entity;


import java.sql.Blob;

public class Student extends Teacher{

    private String label;

    public Student(String user, String pwd, byte[] headshot, String school, String id, String name, boolean sex, String collage, String mail, int no, String label) {
        super(user, pwd, headshot, school, id, name, sex, collage, mail, no);
        this.label = label;
    }

    @Override
    public String toString() {
        return "Student{" +
                "user='" + user + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
