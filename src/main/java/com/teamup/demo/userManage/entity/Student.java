package com.teamup.demo.userManage.entity;


public class Student extends Teacher{

    private String label;

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
