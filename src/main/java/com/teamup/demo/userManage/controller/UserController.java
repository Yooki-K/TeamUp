package com.teamup.demo.userManage.controller;


import com.teamup.demo.tool.Custom;
import com.teamup.demo.tool.Message;
import com.teamup.demo.tool.Util;
import com.teamup.demo.userManage.entity.*;
import com.teamup.demo.userManage.service.CodeService;
import com.teamup.demo.userManage.service.UserService;
import com.teamup.demo.tool.SendMail;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

//get才判断session,post默认有session

@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private CodeService codeService;
    @Resource
    Admin admin;
    @Resource
    Custom custom;

    @PostMapping(value = "/signIn")
    //注册 传参type 1学生 2老师 ，只需输入参数 {user,pwd,mail}
    public Message signIn(@Param(value = "type") String type, Student user){
        if(userService.addUser(user, Integer.parseInt(type)) == 1){
            return new Message(true,"注册成功(sign in success)");
        }else{
            return new Message(false,"注册失败(sign in fail)");
        }
    }
    @GetMapping(value = "/sign-up-page/1")
    public ModelAndView sign_up_teacher(HttpSession session,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign-up-student");
        return modelAndView;
    }
    @GetMapping(value = "/sign-up-page/2")
    public ModelAndView sign_up_student(HttpSession session,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign-up-teacher");
        return modelAndView;
    }
    @GetMapping(value = "/sign-up-page")
    public ModelAndView sign_up_func(HttpSession session,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }
    @GetMapping("/judge/user")
    /*注册时判断用户名是否已使用，焦点离开user input时使用*/
    public Message userIsExist(@RequestParam("user") String user, @RequestParam("table") String table){
        Student USER=userService.findUserByUser(user,table);
        if(USER==null)
            return new Message(false);
        else
            return new Message(true);
    }
    @PostMapping(value = "/getCaptcha")
    //发送邮件验证码 接受数据json 1注册 2忘记密码
    public Message sendCaptcha(@Param(value = "mail") String mail,
                               @Param(value = "user") String user,
                               @Param(value = "type") String type,
                               @Param(value = "table") String table) {
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
    //验证验证码 接受数据json
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
    @PostMapping("/signUp")
    /*登录 参数table 1学生 2老师 3管理员*/
    public ModelAndView signUp(@Param(value="user") String user, @Param(value="pwd") String pwd, @Param(value="table") String table,
                          HttpSession session,HttpServletRequest request){
        String url = request.getRequestURI();
        if(table!=null && table.equals("admin")){//管理员登录
            if(user!=null && user.equals(admin.getUser())){
                if(pwd!=null && pwd.equals(admin.getPwd())){
                    session.setAttribute("table",table);
                    session.setMaxInactiveInterval(60*60);//设置一小时后过期
                    ModelAndView modelAndView = new ModelAndView("redirect:/root/identify-manage");
                    modelAndView.addObject("user",admin);
                    return modelAndView;
                }
                return Util.createError("false","密码错误(password error)",url);
            }
            return Util.createError("false","管理员用户名错误(admin username error)",url);
        }
        Student USER = userService.findUserByUser(user,table);
        if (USER == null)
            return Util.createError("false","用户不存在(user does not exist)",url);
        if (pwd != null && pwd.equals(USER.getPwd())) {
            session.setAttribute("user",USER);
            session.setAttribute("table",table);
            session.setMaxInactiveInterval(60*60);//设置一小时后过期
            ModelAndView modelAndView = new ModelAndView("redirect:/rooms/public");//组队大厅
            return modelAndView;
        }
        else {
            return Util.createError("false","密码错误(password error)",url);
        }
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
    public Message addCertification(Certification certification, HttpSession session){
        Student user = (Student) session.getAttribute("user");
        String table = session.getAttribute("table").toString();
        certification.setUser(user.getUser());
        certification.setType(table.equals("student")?1:2);
        if(userService.addCertification(certification)>0){
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
    @GetMapping(value = "/root/identify-manage")
    public ModelAndView identify(HttpSession session,HttpServletRequest request){
        Student user = (Student) session.getAttribute("user");
        if (user==null)
            return Util.createError("false","请先登录","/");
        if(user.getUser()!= admin.getUser()){
            return Util.createError("false","只有管理员有权限访问",request.getRequestURI());
        }
        ModelAndView modelAndView = new ModelAndView();
        Map<String, List<Certification>> clist = this.queryCertification();
        modelAndView.addObject("students",clist.get("student"));
        modelAndView.addObject("teachers",clist.get("teacher"));
        modelAndView.setViewName("manager");
        return modelAndView;
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
    @PostMapping("/update/user")
    /*修改个人资料*/
    public Message updateLabel(@Param("label") String label,HttpSession session,
                               @Param("user")String user){
        Student USER = (Student) session.getAttribute("user");
        String table =  session.getAttribute("table").toString();
        Student become = userService.findUserByUser(user,table);
        if (become!=null && !USER.getUser().equals(user))
            return new Message(false,"该用户名已使用");
        Map<String,String>map = new HashMap<>();
        map.put("label",label);
        map.put("user",user);
        if(userService.updateUser(USER,map) > 0) {
            USER.setUser(user);
            USER.setLabel(label);
            session.setAttribute("user", USER);
            return new Message(true, "更改个人信息成功");
        }
        else
            return new Message(false,"更改个人信息失败");
    }
    @RequestMapping(value = "/upload/headshot",method = RequestMethod.POST)
    public Message uploadHeadshot(HttpSession session,
                                @Param("file") MultipartFile file){
        Student USER = (Student) session.getAttribute("user");
        Map<String,String>map = new HashMap<>();
        if (file!=null){
            String base64;
            try {
                base64 = Util.byteToBase64(file.getBytes(),file.getContentType());
            } catch (IOException e) {
                e.printStackTrace();
                return new Message(false,"头像文件上传失败");
            }
            map.put("headshot",base64);
            USER.setHeadshot(base64);
        }
        if(map.size()!= 0 && userService.updateUser(USER,map) > 0) {
            session.setAttribute("user", USER);
            System.out.println("修改成功");
            return new Message(true);
        }
        else
            return new Message(false,"更换头像失败");
    }
    @GetMapping("/loginOut")
    /*登出*/
    public ModelAndView loginOut(HttpSession session){
        System.out.println("登出");
        session.removeAttribute("user");
        return new ModelAndView("index");
    }
    @RequestMapping(value = "/data/query/{table}/fuzzy",method = RequestMethod.GET)
    /*按用户名模糊查询*/
    public List<Student> fuzzyMatchUsersById(@PathVariable String table,
                                             @RequestParam("param")String param){
        return userService.fuzzyMatchUsersByUser(param,table);
    }
    @GetMapping(value = "/{studentNo}/identify")
    //认证页面
    public ModelAndView identify(HttpSession session, @PathVariable int studentNo,
                                 HttpServletRequest request){
        Student user = (Student) session.getAttribute("user");
        if(user==null)
            return Util.createError("false","请先登录","/");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("identify");
        return modelAndView;
    }
    @GetMapping(value = "/{studentNo}/index")
    public ModelAndView index(@PathVariable int studentNo,HttpSession session,HttpServletRequest request){
        Student user = (Student) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        Student person = null;
        if(studentNo>50000)
            person = userService.findUserByNo(studentNo,"teacher");
        else
            person = userService.findUserByNo(studentNo,"student");
        if(person==null)
            return Util.createError("404","当前资源不存在",request.getRequestURI());
        modelAndView.addObject("person",person);
        modelAndView.addObject("user",user);

        modelAndView.addObject("schools",custom.getSchools());
        modelAndView.setViewName("my");
        return modelAndView;
    }
}
