package com.teamup.demo.teamManage.service;

import com.teamup.demo.teamManage.entity.Evaluation;
import com.teamup.demo.teamManage.entity.Team;
import com.teamup.demo.teamManage.entity.TeamInf;
import com.teamup.demo.teamManage.mapper.TeamMapper;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service(value = "TeamService")
public class TeamService {
    @Resource
    private TeamMapper teamMapper;

    public List<TeamInf> getTeamInfByUser(Student student){
        return teamMapper.getTeamInfByUser(student.getUser());
    }
    public List<Team> getTeamByClass(int classId){
        return teamMapper.getTeamByClass(classId);
    }
    public List<Evaluation> getEvaluation(String user){
        return teamMapper.getEvaluationByUser(user);
    }
    public int addTeamIfs(Teacher teacher,int classId){
        try {
            return teamMapper.addTeamInf(classId, teacher.getNo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
    public int addEvaluation(Evaluation evaluation){
        try {
            return teamMapper.addEvaluation(evaluation);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public int getNumNeedTeam(Student student){
        return teamMapper.getNumNeedTeam(student.getUser());
    }

}