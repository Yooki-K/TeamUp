package com.teamup.demo.userManage.controller;


import com.teamup.demo.tool.Message;
import com.teamup.demo.userManage.entity.*;
import com.teamup.demo.userManage.service.CodeService;
import com.teamup.demo.userManage.service.UserService;
import com.teamup.demo.tool.SendMail;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

//get才判断session,post默认有session

@RestController
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private CodeService codeService;

    @PostMapping(value = "/signIn")
//    注册 传参type 1学生 2老师 ，只需输入参数 {user,pwd,mail}
    public Message signIn(@Param(value = "type") String type, Student user){
        if(userService.addUser(user, Integer.parseInt(type)) == 1){
            return new Message(true,"注册成功(sign in success)");
        }else{
            return new Message(false,"注册失败(sign in fail)");
        }
    }
    @PostMapping(value = "/getCaptcha/{table}")
//    发送邮件验证码 接受数据json 1注册 2忘记密码
    public Message sendCaptcha(@Param(value = "mail") String mail,
                               @Param(value = "user") String user,
                               @Param(value = "type") String type,
                               @PathVariable String table) {
        int t = Integer.parseInt(type);
        int num=0;
        if(t==1){
            String code = SendMail.sendSimpleMail(mail);
            num = codeService.addCode(new MailCode(user, code, t));
        }else{//mail为null
            Student USER = userService.findUserByUser(user,table);
            String code = SendMail.sendSimpleMail(USER.getMail());
            num = codeService.addCode(new MailCode(user,code,t));
        }
            if(num >= 1)
                return new Message(true);
            else
                return new Message(false,"验证码失效(captcha invalid)");
    }
    @PostMapping(value = "/verifyCode")
//    验证验证码 接受数据json
    public Message verifyCode(@Param(value = "user") String user,
                              @Param(value = "captcha") String captcha,
                              @Param(value = "type") String type){
        long time = new Date().getTime();
        codeService.deleteInvalid(time);
        String code = codeService.findCodeByUser(user,time,Integer.parseInt(type));
        if(code!=null)
            if(code.equals(captcha))
                return new Message(true,"注册成功(sign in success)");
            else
                return new Message(false,"验证码错误(captcha error)");
        else
            return new Message(false,"验证码失效(captcha invalid)");
    }
    @PostMapping("/signUp/{table}/")
    /*登录 参数table 1学生 2老师 3管理员*/
    public Message signUp(@Param(value="user") String user, @Param(value="pwd") String pwd, @PathVariable(name="table") String table, HttpSession session){
        Student USER = userService.findUserByUser(user,table);
        if (USER == null)
            return new Message(false,"用户不存在(user does not exist)");
        if (pwd != null && pwd.equals(USER.getPwd())) {
            session.setAttribute("user",USER);
            session.setAttribute("table",table);
            session.setMaxInactiveInterval(60*60);//设置一小时后过期
            return new Message(true,"登陆成功(sign up success)");
        }
        else
            return new Message(false,"密码错误(password error)");
    }

    @PostMapping("/updatePwd")
    /*修改密码 参数 原密码*/
    public Message updatePwd(@Param(value = "pwd1")String pwd1,
                             @Param(value = "pwd2")String pwd2,
                             HttpSession session){
        Student user= (Student) session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        if(user.getPwd().equals(pwd1)){
            if (user.getPwd().equals(pwd2))
                return new Message(false,"新密码与原密码相同");
            int num = userService.updatePwdByUser(user.getUser(),pwd2,table);
            if(num>=1)
                return new Message(true,"修改密码成功(update password success)");
            else
                return new Message(false,"修改密码失败");
        }else
            return new Message(false,"原密码输入错误");
    }
    @PostMapping("/certificate")
    /*实名认证 可用form表单提交 参数 学号*/
    public Message addCertification(Certification certification,HttpSession session){
        Student user = (Student) session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        certification.setUser(user.getUser());
        certification.setType(table.equals("student")?1:2);
        if(userService.addCertification(certification)>0)
            return new Message(true,"实名认证已提交，请等待审核");
        else
            return new Message(false,"实名认证提交失败，请重新提交");
    }

    @PostMapping("/queryCertification")
    /*管理员查询实名认证 返回Map,可当作json使用*/
    public Map<String, List<Certification>> queryCertification(){
        Map<String, List<Certification>> cList = new HashMap<String, List<Certification>>();
        cList.put("student",userService.getCertificationByType(1));
        cList.put("teacher",userService.getCertificationByType(2));
        return cList;
    }
    @PostMapping("/agreeCertification")
    /*管理员查询实名认证 返回Map,可当作json使用*/
    public Message agreeCertification(List<String>userList){
        int num = userService.agreeCertification(userList);
        if (num>0)
            return new Message(true,String.format("同意%d条申请",num));
        else
            return new Message(false,"审核失败");
    }
}
