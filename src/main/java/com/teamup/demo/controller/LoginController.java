package com.teamup.demo.controller;


import com.teamup.demo.entity.MailCode;
import com.teamup.demo.entity.Message;
import com.teamup.demo.entity.Student;
import com.teamup.demo.service.CodeService;
import com.teamup.demo.service.StuService;
import com.teamup.demo.tool.SendMail;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    private StuService stuService;
    @Autowired
    private CodeService codeService;

    @PostMapping(value = "/sign-in")
//    注册 传参type 1学生 2老师 ，只需输入参数 {user,pwd,mail,sex}
    public void singIn(@RequestParam(value = "type") int Type,Object obj){
        if(Type==1){
            Student student = (Student) obj;
            stuService.addStu(student);
        }else{

        }

    }
    @PostMapping(value = "/getCaptcha")
//    发送邮件验证码 接受数据json
    public Message sendCaptcha(@Param(value = "mail") String mail,
                              @Param(value = "user") String user) {
            String code = SendMail.sendSimpleMail(mail);
            int num = codeService.addCode(new MailCode(user, code));
            if(num >= 1)
                return new Message(true);
            else
                return new Message(false,"c");
    }
    @PostMapping(value = "/verifyCode")
//    验证验证码 接受数据json
    public Message verifyCode(@Param(value = "user") String user,
                               @Param(value = "captcha") String captcha){
        Map<String,Object>map = new HashMap<>();
        map.put("user",user);
        map.put("time",new Date().getTime());
        System.out.println(captcha);
        String code = codeService.findCodeByUser(map);
        System.out.println(code);
        if(code!=null)
            if(code.equals(captcha))
                return new Message(true,"注册成功(sign in success)");
            else
                return new Message(false,"验证码错误(captcha error)");
        else
            return new Message(false,"验证码失效(captcha invalid)");
    }

}
