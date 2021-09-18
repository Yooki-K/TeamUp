package com.teamup.demo.userManage.controller;


import com.teamup.demo.tool.Custom;
import com.teamup.demo.tool.Message;
import com.teamup.demo.userManage.entity.*;
import com.teamup.demo.userManage.service.CodeService;
import com.teamup.demo.userManage.service.UserService;
import com.teamup.demo.tool.SendMail;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

//get才判断session,post默认有session

@RestController
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private CodeService codeService;
    @Resource
    Admin admin;

    @PostMapping(value = "/signIn")
//    注册 传参type 1学生 2老师 ，只需输入参数 {user,pwd,mail}
    public Message signIn(@Param(value = "type") String type, Student user){
        if(userService.addUser(user, Integer.parseInt(type)) == 1){
            return new Message(true,"注册成功(sign in success)");
        }else{
            return new Message(false,"注册失败(sign in fail)");
        }
    }
    @GetMapping("/judge/user/{table}")
    /*注册时判断用户名是否已使用，焦点离开user input时使用*/
    public Message userIsExist(@RequestParam("user") String user, @PathVariable String table){
        Student USER=userService.findUserByUser(user,table);
        if(USER==null)
            return new Message(false);
        else
            return new Message(true);
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
    public Message signUp(@Param(value="user") String user, @Param(value="pwd") String pwd, @PathVariable(name="table") String table,
                          HttpSession session, HttpServletRequest request,HttpServletResponse response,
                          @CookieValue(value = "isCertificated",defaultValue = "false")String v){
        if(table!=null && table.equals("admin")){//管理员登录
            if(user!=null && user.equals(admin.getUser())){
                if(pwd!=null && pwd.equals(admin.getPwd())){
                    session.setAttribute("table",table);
                    session.setMaxInactiveInterval(60*60);//设置一小时后过期
                    return new Message(true,"登陆成功(sign up success)");
                }
                return new Message(false,"密码错误(password error)");
            }
            return new Message(false,"管理员用户名错误(admin username error)");
        }
        Student USER = userService.findUserByUser(user,table);
        if (USER == null)
            return new Message(false,"用户不存在(user does not exist)");
        if (pwd != null && pwd.equals(USER.getPwd())) {
            session.setAttribute("user",USER);
            session.setAttribute("table",table);
            session.setMaxInactiveInterval(60*60);//设置一小时后过期
            if(v.equals("waiting")){
                Certification certification = userService.findCertificationByUser(USER.getUser());
                if(certification!=null && certification.isAgree()){
                    //清除cookie
                    Cookie cookie = new Cookie("isCertification",null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);

                }
            }
            return new Message(true,"登陆成功(sign up success)");
        }
        else
            return new Message(false,"密码错误(password error)");
    }

    @PostMapping("/update/pwd")
    /*修改密码 参数 原密码*/
    public Message updatePwd(@Param(value = "pwd1")String pwd1,
                             @Param(value = "pwd2")String pwd2,
                             HttpSession session){
        Student user= (Student) session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        if(table.equals("admin")){
            return new Message(false,"管理员不能通过此方式更改密码");
        }
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
    /*实名认证 可用form表单提交 参数 学号、学校、姓名、性别*/
    public Message addCertification(Certification certification, HttpSession session,
                                    HttpServletResponse response){
        Student user = (Student) session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        certification.setUser(user.getUser());
        certification.setType(table.equals("student")?1:2);
        if(userService.addCertification(certification)>0){
            Cookie cookie = new Cookie("isCertificated","waiting");
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
            return new Message(true,"实名认证已提交，请等待审核");
        }

        else
            return new Message(false,"实名认证提交失败，请重新提交");
    }

    @PostMapping("/data/query/certification")
    /*管理员查询实名认证 返回Map,可当作json使用*/
    public Map<String, List<Certification>> queryCertification(){
        Map<String, List<Certification>> cList = new HashMap<String, List<Certification>>();
        cList.put("student",userService.getCertificationByType(1));
        cList.put("teacher",userService.getCertificationByType(2));
        return cList;
    }
    @PostMapping("/operateCertification/{operate}")
    /*管理员审核实名认证，可当作json使用  operate{agree,disagree}*/
    public Message operateCertification(@RequestBody List<String> users, @PathVariable String operate){
        int num = userService.operateCertification(users,operate.equals("agree")?"true":"false");
        if (num>0)
            return new Message(true,String.format("%s %d application",operate,num));
        else
            return new Message(false,"审核失败");
    }
    @PostMapping("/update/label")
    /*设置个人标签*/
    public Message updateLabel(@Param("label") String label,HttpSession session){
        Student user= (Student) session.getAttribute("user");
        if(userService.updateLabelByUser(user,label) > 0)
            return new Message(true,"更改个人标签成功");
        else
            return new Message(false,"更改个人标签失败");
    }
    @RequestMapping(value = "/upload/headshot",method = RequestMethod.POST)
//    上传头像 <form action="/t" method="post" enctype="multipart/form-data">
    public Message uploadHeadshot(@RequestParam("file") MultipartFile file, HttpSession session) {
        Student user = (Student) session.getAttribute("user");
        String table =  session.getAttribute("table").toString();
        int num= 0;
        try {
            num = userService.updateHeadshotByUser(
                    userService.findUserByUser(user.getUser(),table),
                    file.getBytes(),"student"
            );
        } catch (IOException e) {
            e.printStackTrace();
            return new Message(false,"上传文件错误");
        }
        if (num>0)
            return new Message(true,"上传头像成功");
        else
            return new Message(false,"服务器出错");
    }

    @GetMapping("/loginOut")
    /*登出*/
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:{}";//todo 主页
    }

}
