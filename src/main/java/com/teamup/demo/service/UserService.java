package com.teamup.demo.service;

import com.teamup.demo.entity.Admin;
import com.teamup.demo.entity.Student;
import com.teamup.demo.entity.Teacher;
import com.teamup.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
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
                    return userMapper.addStu(user);
                else
                    return userMapper.addTea(user);
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
    public String findPwdByUser(String user,String table) {
        try {
            return userMapper.findPwdByUser(user,table);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
}