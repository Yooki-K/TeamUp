package com.teamup.demo.classManage.controller;

import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.classManage.entity.ClassInf;
import com.teamup.demo.classManage.service.ClassService;
import com.teamup.demo.teamManage.entity.Team;
import com.teamup.demo.teamManage.service.TeamService;
import com.teamup.demo.tool.ExcelUtil;
import com.teamup.demo.tool.Message;
import com.teamup.demo.tool.Util;
import com.teamup.demo.userManage.entity.Student;
import com.teamup.demo.userManage.entity.Teacher;
import com.teamup.demo.userManage.service.UserService;
import jxl.read.biff.BiffException;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class ClassController {
    @Resource
    private ClassService classService;
    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;
//    创建班级 传递参数为json 只用传 班级名name 参数
    @PostMapping("/create/class")
    public Message createClass(@RequestBody Class c, HttpSession session){
        Teacher teacher = (Teacher)session.getAttribute("user");
        if (teacher.getName()==null)
            return new Message(false,"请先完成实名认证");
        c.setUser(teacher.getUser());
        String invCode = Util.generateString(6);
        while (classService.codeIsExist(invCode)!=null){
            invCode = Util.generateString(6);
        }
        c.setInvCode(invCode);
        if(classService.addClass(c)>0)
            return new Message(true,"班级"+c.getInvCode()+"创建成功");
        else
            return new Message(false,"班级创建失败");
    }
//    老师查询所建班级
    @PostMapping("/data/query/class/teacher")
    public List<Class> queryClass1(HttpSession session){
        Student user = (Student)session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        return classService.getClassByUser(user,table);
    }

//    查询班级成员
    @PostMapping("/data/query/classMembers")
    public List<Student> queryClassMembers(@Param("classId") int classId,@Param("isNotTeam") boolean isNotTeam){
        if (isNotTeam)
            return teamService.getStuNotTeam(classId);
        return classService.getStuByClassId(classId);
    }
//    学生查询所在班级
    @GetMapping("/{studentNo}/class")
    public ModelAndView queryClass2(HttpSession session, @PathVariable int studentNo,HttpServletRequest request){
        Student user = (Student)session.getAttribute("user");
        if (user==null) {
            return Util.createError("false","请先登录","index");
        }
        if (studentNo!=user.getNo()){
            return Util.createError("404","访问错误页面",request.getRequestURI());
        }else
        {
            ModelAndView mav = new ModelAndView();
            String table = session.getAttribute("table").toString();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            List<Class> classList = classService.getClassByUser(user, table);
            for (Class c : classList
            ) {
                Map<String, Object> map = new HashMap<String, Object>();
                Teacher teacher = classService.getTeaByClassId(c.getId());
                map.put("class", c);
                map.put("teacherNo", teacher.getNo());
                if (teacher.getName()==null)
                    map.put("teacherName", teacher.getName());
                else
                    map.put("teacherName", "未实名认证");
                list.add(map);
            }
            System.out.println(list);
            mav.addObject("data", list);
            mav.addObject("user", user);
            mav.setViewName("myclass");
            return mav;
        }
    }
    /*老师设置班级公告 json announcement classId*/
    @PostMapping("/update/class/announcement")
    public Message setAnnouncement(@RequestBody Class c,HttpSession session){
       Student user = (Student) session.getAttribute("user");
       Class cc = classService.getClassById(c.getId());
        System.out.println(c.toString());
        if(cc==null)
           return new Message(false,"班级不存在");
       if(!user.getUser().equals(cc.getUser()))
           return new Message(false,"非当前班级老师");
       if (classService.setAnnouncement(cc.getAnnouncement(),cc.getId())>0)
           return new Message(true);
       return new Message(false);
    }
    /*删除班级 接受json类型的list数据
    * @Param idList*/
    @PostMapping("/delete/class")
    public Message deleteClass(@RequestBody List<String>idList){
        int num = classService.deleteClassByClassId(idList);
        if (num>0){
            return new Message(true,String.format("成功删除%d个班级",num));
        }else{
            return new Message(false,"删除班级出错，请刷新重试");
        }
    }
    /*导入班级成员名单 仅支持.xls文件 */
    @RequestMapping(value = "/upload/member",method = RequestMethod.POST)
    public Message importClassMember(@RequestParam("file")MultipartFile file,@RequestParam("classId")int classId){
        try {
            List<ClassInf>classInfs = new ArrayList<ClassInf>();
            List<String> userIdList = ExcelUtil.run(file);
            List<Student> students = userService.findUsersById(userIdList);
            for(Student stu:students){
                classInfs.add(new ClassInf(classId,stu.getUser(),stu.getId()));
                userIdList.remove(stu.getId());
            }
            for (String userId:userIdList) {
                classInfs.add(new ClassInf(classId,null,userId));
            }
            int num = classService.joinClass(classInfs);
            if(num>0)
                return new Message(true,String.format("成功导入%d个班级成员，其中%d个成员未实名认证",num,userIdList.size()));
            else
                return new Message(false,"导入成员失败");
        } catch (IOException | BiffException e) {
            e.printStackTrace();
            return new Message(false,"excel文件上传失败");
        }
    }
    /*通过邀请码加入班级*/
    @PostMapping("/join/class")
    public Message joinClass(@Param("invCode")String invCode,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        if(user.getId()==null)
            return new Message(false,"请先十几名认证");
        String classId = classService.codeIsExist(invCode);
        if (classId==null)
            return new Message(false,"当前邀请码不存在");
        if(classService.joinClass(new ClassInf(Integer.parseInt(classId),user.getUser(),user.getId()))>0)
            return new Message(true,"加入此班级成功");
        else
            return new Message(false,"未在班级名单中");
    }

    @GetMapping("/class/{classId}")
    public ModelAndView queryClass(@PathVariable int classId,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        Class c = classService.getClassById(classId);
        List<Team> teams = teamService.getTeamByClass(classId);
        Teacher teacher = classService.getTeaByClassId(classId);
        teacher.setHeadshot(null);
        List<Map<String,Object>> list= new ArrayList<>();
        for(Team team :teams){
            Map<String,Object>map = new HashMap<>();
            map.put("team",team);
            Student leader = userService.findUserByUser(team.getLeader(),"student");
            map.put("leaderName",leader.getName());
            map.put("leaderNo",leader.getNo());
            list.add(map);
        }
        ModelAndView modelAndView = new ModelAndView("class-page");
        modelAndView.addObject("user",user);
        modelAndView.addObject("class",c);
        modelAndView.addObject("teams",list);
        modelAndView.addObject("teacher",teacher);
        return modelAndView;
    }
}
