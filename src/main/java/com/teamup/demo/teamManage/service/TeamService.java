package com.teamup.demo.teamManage.service;

import com.teamup.demo.teamManage.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

@Service(value = "TeamService")
public class TeamService {
    @Resource
    private TeamMapper teamMapper;
//    public int updateTeamInf(){
//
//    }
    public int setTeamId(String user,int classId,int teamId){
        try {
            return teamMapper.setTeamId(user, classId, teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
