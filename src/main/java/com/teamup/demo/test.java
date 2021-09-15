package com.teamup.demo;


import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class test {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String Test1(@CookieValue(value = "isCertificated",defaultValue = "false")String v,
                        HttpServletResponse response) {
        Student student=new Student();
        student.setUser("hzj");

        System.out.println(userService.certification(student.getUser(),userService.findCertificationByUser(student.getUser())));
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

    @PostMapping("/t1")
    public String Test3(@Param("ll") String ll,Student user){
        System.out.println(user.toString());
        System.out.println(ll);
        int num = userService.updateLabelByUser(user,ll);
        return String.format("%d",num);
    }

}
