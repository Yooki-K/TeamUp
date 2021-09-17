package com.teamup.demo;


import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.classManage.service.ClassService;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import com.teamup.demo.userManage.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class test {

    @Autowired
    private UserService userService;
    @Autowired
    private ClassService classService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView Test1() {
        return new ModelAndView("test");
    }

    @PostMapping("/findbyuser")
    public String Test2(@Param(value="user") String user){
        System.out.println(user);
        Student u = userService.findUserByUser(user,"student");
        if(u!=null)
            return u.toString();
        else
            return "null!";
    }

    @RequestMapping(value = "/t",method = RequestMethod.POST)
    public String Test3(@RequestParam("file")MultipartFile file,
                        @RequestParam("user")String user) {
        try {
            System.out.println(file.getName());
            System.out.println(file.getSize());
            int num=userService.updateHeadshotByUser(
                    userService.findUserByUser(user,"student"),
                    file.getBytes(),"student"
            );
            System.out.println(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "!!!";
    }

}
