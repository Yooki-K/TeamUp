package com.teamup.demo.service;

import com.teamup.demo.entity.Student;
import com.teamup.demo.mapper.StuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service(value = "StuService")
public class StuService {

    @Resource
    private StuMapper stuMapper;

    public List<Student> getAllStu() {
        return stuMapper.getAllStu();
    }
    public Student findStuByUser(String user) { return stuMapper.findStuByUser(user); }
    public String addStu(Student student) {
            try {
                stuMapper.addStu(student);
                return student.getUser();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
}