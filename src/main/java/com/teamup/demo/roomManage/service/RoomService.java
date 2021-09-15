package com.teamup.demo.roomManage.service;

import com.teamup.demo.roomManage.mapper.RoomMapper;
import com.teamup.demo.userManage.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "RoomService")
public class RoomService {

    @Resource
    private RoomMapper roomMapper;

    public List<Student> matchStuByLabel(Student stu, String aim){
        return roomMapper.matchStuByLabel(stu.getUser(),aim);
    }
}
