package com.teamup.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//application/x-www-form-urlencoded  @Param
//application/json @RequestBody

@SpringBootApplication
@EnableTransactionManagement   //开启事务管理
@MapperScan({"com.teamup.demo.userManage.mapper",
        "com.teamup.demo.roomManage.mapper",
        "com.teamup.demo.classManage.mapper",
        "com.teamup.demo.teamManage.mapper"})//扫包
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

