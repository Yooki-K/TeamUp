package com.teamup.demo;


import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.classManage.service.ClassService;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import com.teamup.demo.userManage.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class test {

    @Resource
    private UserService userService;
//    @Autowired
//    private ClassService classService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView Test1() {
        return new ModelAndView("test");
    }

    @PostMapping("/fuzzyMatchUsers")
    public String Test2(@Param(value="param") String param,
                        @Param(value="table") String table){
        System.out.println(param);
        List<Student> u = userService.fuzzyMatchUsersByUser(param,table);
        for(Student uu:u){
            System.out.println(uu.toString());
        }
        if(u!=null)
            return "yes";
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
