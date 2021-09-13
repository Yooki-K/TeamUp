package com.teamup.demo.userManage.service;

import com.teamup.demo.userManage.entity.Certification;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import com.teamup.demo.userManage.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            return userMapper.updatePwdByUser(user,pwd,table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int addCertification(Certification certification){
        try {
            return userMapper.addCertification(certification);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public List<Certification> getCertificationByType(int type){
        return userMapper.getCertificationByType(type);
    }
    public int agreeCertification (List<String> userList){
        try {
            return userMapper.agreeCertification(userList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}