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

}
