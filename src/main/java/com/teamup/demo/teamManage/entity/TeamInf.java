package com.teamup.demo.teamManage.entity;

public class TeamInf {
    int id;
    int classId;
    int teamId;
    String user;
    boolean isTeam;
    int teacherId;

    public TeamInf(int id, int classId, int teamId, String user, boolean isTeam, int teacherId) {
        this.id = id;
        this.classId = classId;
        this.teamId = teamId;
        this.user = user;
        this.isTeam = isTeam;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isTeam() {
        return isTeam;
    }

    public void setTeam(boolean team) {
        isTeam = team;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
