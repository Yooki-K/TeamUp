package com.teamup.demo.roomManage.mapper;

import com.teamup.demo.roomManage.entity.Room;
import com.teamup.demo.userManage.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public interface RoomMapper {
    List<Student> matchStuByLabel(@Param("user")String user, @Param("aim")String aim);//智能匹配

    int addRoom(Room room)throws SQLException;//创建房间
    List<Room> getRoomByClass(String classId);//根据班级查询所有房间
    List<Room> getRoomPublic();//返回所有classId为null的房间，即公共大厅的房间
    List<Room> getRoomByTag(String tag);//返回所有tag标签可以匹配(like)到参数tag的房间
    List<Student> getStuByRoom(String roomId);//查找一个房间的所有已加入成员
    int addStuById(String user,String roomId)throws SQLException;//根据房间编号添加成员
    int removeStuById(String user,String roomId)throws SQLException;//根据房间编号移除成员
    //todo 创建sql_2
    int updateRoomById(Map<String,String> map)throws SQLException;//修改房间信息

}
