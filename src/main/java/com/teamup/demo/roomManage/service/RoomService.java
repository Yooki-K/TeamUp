package com.teamup.demo.roomManage.service;

import com.teamup.demo.roomManage.entity.ApplyRoom;
import com.teamup.demo.roomManage.entity.Invitation;
import com.teamup.demo.roomManage.entity.Room;
import com.teamup.demo.roomManage.mapper.RoomMapper;
import com.teamup.demo.userManage.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service(value = "RoomService")
public class RoomService {

    @Resource
    private RoomMapper roomMapper;

    public int addRoom(Room room){
        try {
            return roomMapper.addRoom(room);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int deleteRoom(Student student,int roomId){
        try {
            return roomMapper.deleteRoom(student.getUser(),roomId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int updateRoomById(Map<String,String>map,int roomId){
        try {
            return roomMapper.updateRoomById(map,roomId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }
    public int addStuByRoom(String user,int roomId){
        try {
            return roomMapper.addStuById(user, roomId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int removeStuByRoom(String user,int roomId){
        try {
            return roomMapper.removeStuById(user, roomId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int addInvitation(Invitation invitation){
        try {
            return roomMapper.addInvitation(invitation);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int operateInvitation(boolean isAgree,int[] id,String user){
        try {
            return roomMapper.operateInvitation(isAgree, id, user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
    public int addApplication(ApplyRoom applyRoom){
        try {
            return roomMapper.addApplication(applyRoom);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int operateApplication(boolean isAgree,int[] id){
        try {
            return roomMapper.operateApplication(isAgree, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
    public int getApplicationNum(int roomId){
        return roomMapper.getApplicationNum(roomId);
    }
    public List<ApplyRoom> getApplicationByRoom(int roomId){
        return roomMapper.getApplicationByRoom(roomId);
    }
    public List<ApplyRoom> getApplicationByUser(String user,int tt){
        return roomMapper.getApplyRoomByUser(user,tt);
    }

    public List<Invitation> getInvitation(String user,String type,int tt){
        return roomMapper.getInvitation(user, type,tt);
    }

    public List<Student> getStuByRoom(int roomId){
        return roomMapper.getStuByRoom(roomId);
    }
    public List<Student> matchStuByLabel(Student stu, String aim){
        return roomMapper.matchStuByLabel(stu.getUser(),aim);
    }
    public List<Room> getRoomByClass(int classId){
        return roomMapper.getRoomByClass(classId);
    }
    public List<Room> getRoomPublic(String sort,boolean isAsc){
        return roomMapper.getRoomPublic(sort, isAsc);
    }
    public List<Room> getRoomByTag(String tag){
        return roomMapper.getRoomByTag(tag);
    }
    public Room getRoomById(int roomId){
        return roomMapper.getRoomById(roomId);
    }
    public List<Room> getRoomByName(String name){
        return roomMapper.getRoomByName(name);
    }
    public List<Room> getRoomByUser(String user){
        return roomMapper.getRoomByUser(user);
    }
    public Room findRoomById(int roomId){
        return roomMapper.findRoomById(roomId);
    }
}
