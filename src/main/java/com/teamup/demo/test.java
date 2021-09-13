package com.teamup.demo;


import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.service.UserService;
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
        System.out.println(user);
        Student u = userService.findUserByUser(user,"student");
        if(u!=null)
            return u.toString();
        else
            return "null!";
    }

    @PostMapping("/t")
    public void Test3(Student user){
//        System.out.println(user.toString());
//        return userService.addUser2(user);
    }
}
