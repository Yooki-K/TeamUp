package com.teamup.demo.roomManage.controller;


import com.teamup.demo.roomManage.service.RoomService;
import com.teamup.demo.tool.Custom;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoomController {
    @Resource
    private RoomService roomService;
    @Resource
    Custom custom;
    @Resource
    private UserService userService;
    @PostMapping("/matchTeammate")
//    匹配 intel智能匹配  key 关键字匹配
    public Map<String,List<Student>> intelMatch(@Param("type") String type, @Param("aim") String aim,HttpSession session){
        Student user= (Student) session.getAttribute("user");
        Map<String,List<Student>> map =new HashMap<>();
        if(type.equals("intel")){//    智能匹配
            for (String x: custom.getLabels()
                 ) {
                if (user.getLabel()!=null && x.equals(user.getLabel())) continue;
                map.put(x,roomService.matchStuByLabel(user,x));
            }
        }else if(type.equals("key")){//    关键字匹配
            if(aim!=null)
                map.put(aim,roomService.matchStuByLabel(user,aim));
            else
                map.put("error",null);
        }
        else
            map.put("error",null);
        return map;
    }
}
