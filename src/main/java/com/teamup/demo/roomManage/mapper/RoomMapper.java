package com.teamup.demo.roomManage.mapper;

import com.teamup.demo.roomManage.entity.Invitation;
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
    List<Room> getRoomByClass(int classId);//根据班级查询所有房间
    List<Room> getRoomPublic(@Param("sort")String sort,@Param("isAsc") boolean isAsc);//返回所有classId为null的房间，即公共大厅的房间
    List<Room> getRoomByTag(String tag);//返回所有tag标签可以匹配(like)到参数tag的房间
    List<Room> getRoomByName(String name);//返回所有name房间名可以匹配(like)到参数name的房间
    List<Student> getStuByRoom(int roomId);//查找一个房间的所有已加入成员
    int addStuById(@Param("user")String user,@Param("roomId")int roomId)throws SQLException;//根据房间编号添加成员
    int removeStuById(@Param("user") String user,@Param("roomId")int roomId)throws SQLException;//根据房间编号移除成员
    int updateRoomById(@Param("map") Map<String,String> map,@Param("id")int id)throws SQLException;//修改房间信息
    int deleteRoom(@Param("user")String user,@Param("id")int id)throws SQLException;//房主删除所建房间
    Room findRoomById(@Param("id")int id);
    int addInvitation(Invitation invitation)throws SQLException;
    int operateInvitation(@Param("isAgree")boolean isAgree,@Param("idList")int[] id)throws SQLException;
    List<Invitation> getInvitation(@Param("user")String user,@Param("type")String type);
}
