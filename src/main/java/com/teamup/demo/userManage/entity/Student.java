package com.teamup.demo.userManage.entity;


import java.sql.Blob;

public class Student extends Teacher{

    private String label;

    public Student(String user, String pwd, String mail, Blob blob, String school, String id, String name, boolean sex, String collage, String label) {
        super(user, pwd, mail, blob, school, id, name, sex, collage);
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
