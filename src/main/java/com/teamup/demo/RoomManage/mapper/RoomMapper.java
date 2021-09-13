package com.teamup.demo.RoomManage.mapper;

import com.teamup.demo.RoomManage.entity.Room;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface RoomMapper {
    //todo 创建sql语句_2
    int addRoom(Room room)throws SQLException;//创建房间
    List<Room> getRoomByClass(String ClassId);//根据班级所有房间
    List<Room> getRoomPublic();//返回所有classId为null的房间
    List<Room> getRoomByTag(String tag);//返回所有tag标签可以匹配(like)到参数tag的房间

}
