package com.teamup.demo.controller;


import com.teamup.demo.entity.Student;
import com.teamup.demo.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class test {

    @Autowired
    private StuService stuService;
    @GetMapping("/")
    public String Test1() {
        return "hello world，这里是java课设";
    }
    @GetMapping("/stu")
    public List<Student> Test2() {
        return stuService.getAllStu();
    }

    @GetMapping("/findbyuser")
    public Student Test3(@RequestParam(value="user") String user){
        return stuService.findStuByUser(user);
    }
}
