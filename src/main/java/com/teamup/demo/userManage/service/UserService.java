package com.teamup.demo.userManage.service;

import com.teamup.demo.userManage.entity.Certification;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import com.teamup.demo.userManage.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

@Service(value = "UserService")
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<Student> getAllStu() {
        return userMapper.getAllStu();
    }
    public List<Teacher> getAllTea() { return userMapper.getAllTea(); }
    public Student findUserByUser(String user,String table) {
        try {
            return userMapper.findUserByUser(user,table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public int addUser(Student user,int Type) {
            try {
                if (Type == 1)
                    return userMapper.addUser(user,"student");
                else
                    return userMapper.addUser(user,"teacher");
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
    public int updatePwdByUser(String user,String pwd,String table){
        try {
            Map<String,String> map = new HashMap<String,String>();
            map.put("pwd",pwd);
            return userMapper.updateUser(user,map,table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int updateLabelByUser(Student stu,String label){
        try {
            Map<String,String> map = new HashMap<String,String>();
            map.put("label",label);
            return userMapper.updateUser(stu.getUser(),map,"student");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int updateHeadshotByUser(Student user,byte[] bytes,String table){
        try {
            return userMapper.updateHeadshot(user.getUser(),bytes,table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public List<Certification> getCertificationByType(int type){
        return userMapper.getCertificationByType(type);
    }
    public int addCertification(Certification certification){
        try {
            return userMapper.addCertification(certification);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int operateCertification (List<String> userList,String value){
        try {
            int num = userMapper.operateCertification(userList,value);
            if(value.equals("agree")){
                List<String> failList = new ArrayList<String>();
                for (String user:userList
                ) {
                    if(!certification(user,findCertificationByUser(user))){
                        failList.add(user);
                        num--;
                    }
                }
                if(!failList.isEmpty())
                    operateCertification(failList,"null");
            }
            return num;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public Certification findCertificationByUser(String user){
        return userMapper.findCertificationByUser(user);

    }
    public boolean certification(String user,Certification certification){
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",certification.getId());
        map.put("school",certification.getSchool());
        map.put("name",certification.getName());
        map.put("sex",certification.isSex()?"true":"false");
        String table = certification.getType() == 1 ? "student":"teacher";
        try {
            if (userMapper.updateUser(user,map,table)>0)
                return true;
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}