package com.teamup.demo.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class SendMail {

    static
    JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        SendMail.javaMailSender = javaMailSender;
    }




    public static String getRandomString(){
        int length=6;
        String str="0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String sendSimpleMail(String toMail){
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("请验证您的账户");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("1486147017@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo(toMail);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        String code = getRandomString();
        message.setText(String.format("欢迎注册大学生组队辅助系统，您的验证码为：%s",code));
        // 发送邮件
        javaMailSender.send(message);

        return code;
    }
}
