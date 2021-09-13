package com.teamup.demo.userManage.entity;

import javax.xml.crypto.Data;
import java.sql.Blob;

public class Room {
    int id;
    String user;
    String name;
    String content;
    String tag;
    Blob background;
    int targetNum;
    int curNum;
    boolean isPwd;
    String pwd;
    Data createTime;
}
