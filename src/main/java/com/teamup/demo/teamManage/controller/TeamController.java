package com.teamup.demo.teamManage.controller;

import com.teamup.demo.teamManage.entity.ChatLog;
import com.teamup.demo.teamManage.entity.Evaluation;
import com.teamup.demo.teamManage.entity.Team;
import com.teamup.demo.teamManage.entity.TeamInf;
import com.teamup.demo.teamManage.service.TeamService;
import com.teamup.demo.tool.Message;
import com.teamup.demo.userManage.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@RestController
public class TeamController {
    @Resource
    private TeamService teamService;

    /*创建评价 json isAnonymous toUser content*/
    @PostMapping("/create/evaluation")
    public Message createEvaluation(@RequestBody Map<String,String> map,
                                    HttpSession session){
        Student user = (Student) session.getAttribute("user");
        Evaluation evaluation=new Evaluation(map.get("toUser"),map.get("content"));
        if (!Boolean.parseBoolean(map.get("isAnonymous")))
            evaluation.setUser1(user.getUser());
        if(teamService.addEvaluation(evaluation) > 0)
            return new Message(true);
        else
            return new Message(false);
    }
    /*获取评价*/
    @GetMapping("/data/query/evaluation")
    public List<Evaluation> queryEvaluation(@RequestParam("user")String user){
        return teamService.getEvaluation(user);
    }

    /*老师发布班级组队任务*/
    @PostMapping("/create/mission")
    public Message createMission(@Param("classId")int classId, HttpSession session){
        Student user = (Student) session.getAttribute("user");
        int num = teamService.addTeamIfs(user,classId);
        if(num>0)
            return new Message(true);
        else
            return new Message(false);
    }
    /*获取组队任务个数*/
    @PostMapping("/data/query/num/needTeam")
    public int queryNeedTeam(HttpSession session){
        Student user = (Student) session.getAttribute("user");
        return teamService.getNumNeedTeam(user);
    }
    /*查看班级内团队*/
    @GetMapping("/data/query/team")
    public List<Team> queryTeamByClass(@RequestParam("classId")int classId){
        return teamService.getTeamByClass(classId);
    }
    /*查看个人组队信息*/
    @PostMapping("/data/query/teamInf")
    public List<TeamInf> queryMyTeamInf(HttpSession session ){
        Student user = (Student) session.getAttribute("user");
        return teamService.getTeamInfByUser(user);
    }
    /*查看班级未组队人数*/
    @GetMapping("/data/query/num/notTeam")
    public int queryNotTeam(@Param("classId")int classId){
        return teamService.getNotTeamNum(classId);
    }
    /*解散团队*/
    @PostMapping("/delete/team")
    public Message dissolveTeam(@Param("teamId")int teamId,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        Team team = teamService.getTeamById(teamId);
        if (team==null)
            return new Message(false,"团队不存在");
        if(!user.getUser().equals(team.getLeader()))
            return new Message(false,"只有团队负责人有权限解散团队");
        if(teamService.dissolveTeam(teamId)>0)
            return new Message(true);
        return new Message(false);
    }

//    todo 待定 发送聊天
//    @PostMapping("/create/chat")
//    public Message sendChat(@Param("content")String content, @Param("teamId")int teamId,
//                            HttpSession session){
//        Student user = (Student) session.getAttribute("user");
//        ChatLog chatLog = new ChatLog(user.getUser(),content,teamId);
//
//    }
//    查看聊天

}
