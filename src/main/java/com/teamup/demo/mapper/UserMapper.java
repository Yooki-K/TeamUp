package com.teamup.demo.mapper;

import com.teamup.demo.entity.Admin;
import com.teamup.demo.entity.Student;
import com.teamup.demo.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserMapper {
    List<Student> getAllStu();
    List<Teacher> getAllTea();
    Student findUserByUser(@Param("user")String user,@Param("table")String table)throws SQLException;
    int addStu(@Param("user")Student user) throws SQLException; //注册
    int addTea(@Param("user")Teacher user) throws SQLException; //注册
    String findPwdByUser(@Param("user")String user, @Param("table")String table) throws SQLException; //登录
    int updatePwdByUser(@Param("user")String user,@Param("pwd")String pwd,@Param("table")String table) throws SQLException;//修改密码
}
