package com.teamup.demo.roomManage.entity;

public class RoomInf {
    private int id;
    private int roomId;
    private String user;

    public RoomInf(int id, int roomId, String user) {
        this.id = id;
        this.roomId = roomId;
        this.user = user;
    }
    public RoomInf(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
