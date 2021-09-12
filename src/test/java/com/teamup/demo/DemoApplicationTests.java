package com.teamup.demo;

import com.teamup.demo.userManage.service.CodeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
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
    @Resource
    CodeService codeService;
    @Test
    void myTest(){

        System.out.println(new Date());
        System.out.println(new Date());
        System.out.println(new Date().toString());
    }
}
