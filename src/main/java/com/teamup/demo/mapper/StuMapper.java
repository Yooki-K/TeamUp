package com.teamup.demo.mapper;

import com.teamup.demo.entity.Student;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface StuMapper {
    List<Student> getAllStu();
    Student findStuByUser(String user);
    void addStu(Student student) throws SQLException; //注册
}
