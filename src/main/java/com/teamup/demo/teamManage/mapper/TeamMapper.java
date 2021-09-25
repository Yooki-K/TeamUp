package com.teamup.demo.teamManage.mapper;

import com.teamup.demo.teamManage.entity.ChatLog;
import com.teamup.demo.teamManage.entity.Evaluation;
import com.teamup.demo.teamManage.entity.Team;
import com.teamup.demo.teamManage.entity.TeamInf;
import com.teamup.demo.userManage.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public interface TeamMapper {
    int updateTeamInfById(@Param("map") Map<String, String> map, @Param("id")int id)throws SQLException;
    int addEvaluation(Evaluation evaluation)throws SQLException;
    int getNumNeedTeam(String user);//查看个人需要组队任务数量
    int addTeamInf(@Param("classId")int classId,@Param("teacherId")int teacherId)throws SQLException;
    int addChat(ChatLog chatLog)throws SQLException;
    List<ChatLog> getChat(@Param("user")String user)throws SQLException;
    List<Evaluation> getEvaluationByUser(String user2);
    List<Team> getTeamByClass(int classId);
    List<Team> getDissolveTeam(String user);
    List<TeamInf> getTeamInfByUser(String user);
    int dissolveTeam(int teamId)throws SQLException;
    Team getTeamById(int teamId);
    List<Student> getStuNotTeam(@Param("classId")int classId);//查看班级未组队成员
    List<Student> getStuByTeamId(@Param("teamId")int teamId);//查看班级未组队成员
}
