package com.teamup.demo.classManage.controller;

import com.teamup.demo.classManage.entity.Class;
import com.teamup.demo.classManage.entity.ClassInf;
import com.teamup.demo.classManage.service.ClassService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class ClassController {
    @Resource
    private ClassService classService;
    @Resource
    private UserService userService;
//    创建班级 传递参数为json 只用传 班级名name 参数
    @PostMapping("createClass")
    public Message createClass(@RequestBody Class c, HttpSession session){
        Teacher teacher = (Teacher)session.getAttribute("user");
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
    @PostMapping("data/queryClass/teacher")
    public List<Class> queryClass1(HttpSession session){
        Student user = (Student)session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        return classService.getClassByUser(user,table);
    }

//    查询班级所有成员
    @PostMapping("data/queryClassMembers")
    public List<Student> queryClassMembers(String classId){
        return classService.getStuByClassId(classId);
    }
//    学生查询所在班级
    @PostMapping("data/queryClass/student")
    public List<Map<String,Object>> queryClass2(HttpSession session){
        Student user = (Student)session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Class>classList = classService.getClassByUser(user,table);
        for (Class c:classList
             ) {
            Map<String,Object>map = new HashMap<String,Object>();
            Teacher teacher = classService.getTeaByClassId(String.valueOf(c.getId()));
            map.put("class",c);
            map.put("teacherNo",teacher.getNo());
            map.put("teacherName",teacher.getName());
            list.add(map);
        }
        return list;
    }
    /*删除班级 接受json类型的list数据
    * @Param idList*/
    @PostMapping("deleteClass")
    public Message deleteClass(@RequestBody List<String>idList){
        int num = classService.deleteClassByClassId(idList);
        if (num>0){
            return new Message(true,String.format("成功删除%d个班级",num));
        }else{
            return new Message(false,"删除班级出错，请刷新重试");
        }
    }
    /*导入班级成员名单 仅支持.xls文件 */
    @RequestMapping(value = "upload/member",method = RequestMethod.POST)
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
    @PostMapping("joinClass")
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
}
