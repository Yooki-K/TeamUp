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
import java.util.Map;

@Service(value = "TeamService")
public class TeamService {
    @Resource
    private TeamMapper teamMapper;

    public List<TeamInf> getTeamInfByUser(Student student){
        return teamMapper.getTeamInfByUser(student.getUser());
    }
    public List<TeamInf> getTeamInfByClassId(int classId){
        return teamMapper.getTeamInfByClassId(classId);
    }
    public List<TeamInf> getNeedTeamInfByUser(String user){
        return teamMapper.getNeedTeamInfByUser(user);
    }
    public List<Team> getTeamByClass(int classId){
        return teamMapper.getTeamByClass(classId);
    }
    public List<Team> getTeamByTeacherNo(int teacherNo){
        return teamMapper.getTeamByTeacherNo(teacherNo);
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
    public int updateTeam(Map<String,String> map,int teamId){
        try {
            return teamMapper.updateTeamById(map,teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public List<Team> getDissolveTeam(String user){
        return teamMapper.getDissolveTeam(user);
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
    public int dissolveTeam(int teamId){
        try {
            return teamMapper.dissolveTeam(teamId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
    public Team getTeamById(int id){
        return teamMapper.getTeamById(id);
    }
    public List<Student> getStuNotTeam(int classId){
        return teamMapper.getStuNotTeam(classId);
    }
    public List<Student> getStuByTeamId(int teamId){
        return teamMapper.getStuByTeamId(teamId);
    }
}
