package com.teamup.demo;

import com.teamup.demo.tool.SendMail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

//    测试成功
//    void myTest() {
//        SendMail.sendSimpleMail("ky_yans@163.com");
//    }
    @Test
    void myTest(){
        long time = new Date().getTime();
        System.out.println(time);
    }
}
