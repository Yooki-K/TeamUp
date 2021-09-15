package com.teamup.demo;

import com.teamup.demo.tool.Custom;
import com.teamup.demo.userManage.service.CodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

//    测试成功
//    void myTest() {
//        SendMail.sendSimpleMail("ky_yans@163.com");
//    }
//    void myTest(){
//
//        long t = codeService.deleteInvalid(new Date().getTime());
//        System.out.println(t);
//    }
//void myTest(){
//    Custom cu = new Custom();
//    System.out.println(custom.toString());
//    System.out.println(cu.toString());
////        Custom{labels=[擅长前端, 擅长后端, 擅长报告, 擅长数据库], schools=[南京理工大学, 南京大学]}
////        Custom{labels=null, schools=null}
//
//}
    @Resource
    CodeService codeService;
    @Test
    void myTest(){
        Custom cu = new Custom();
        System.out.println(cu.toString());

    }
}
