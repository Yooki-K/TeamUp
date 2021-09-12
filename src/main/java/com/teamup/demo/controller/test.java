package com.teamup.demo.controller;


import com.teamup.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class test {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String Test1() {
        return "hello world，这里是java课设";
    }

    @PostMapping("/findbyuser")
    public String Test2(@Param(value="user") String user){
        return userService.findUserByUser(user,"student").toString();
    }

    @PostMapping("/t")
    public int Test3(@Param(value="user") String user,@Param(value="pwd") String pwd){
        return userService.updatePwdByUser(user,pwd,"student");
    }
}
