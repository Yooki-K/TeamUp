package com.teamup.demo;


import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.classManage.service.ClassService;
import com.teamup.demo.teamManage.service.TeamService;
import com.teamup.demo.tool.Message;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import com.teamup.demo.userManage.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class test {

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView  Test1(HttpSession session) {
//        return new ModelAndView("test");
        Student student = userService.findUserByNo(10008,"student");
        session.setAttribute("user",student);
        session.setAttribute("table","student");
//        String url =String.format("redirect:/%d/main",student.getNo());
        String url="redirect:/rooms/public";
        System.out.println(url);
        System.out.println(student.toString());
        return new ModelAndView(url);
//        return new ModelAndView("index");
    }

    @PostMapping("/fuzzyMatchUsers")
    public Message Test2(@Param(value="user") String user,
                        @Param(value="classId") int classId){
        Student student = userService.findUserByUser(user,"teacher");
        System.out.println(student.toString());
        int num = teamService.addTeamIfs(student,classId);
        if(num>0)
            return new Message(true);
        else
            return new Message(false);
    }

    @RequestMapping(value = "/t",method = RequestMethod.POST)
    public String Test3(@RequestParam("file")MultipartFile file,
                        @RequestParam("user")String user) {
        return "!!!";
    }

}
