package com.teamup.demo.tool;

import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class Util {
    /*将Date转换为String*/
    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
    public static String generateString(int length) {
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = str.charAt(random.nextInt(str.length()));
        }
        return new String(text);
    }
    public static String getImgType(byte[] data) {
        if (data[1] == 'P' && data[2] == 'N' && data[3] == 'G') {
            return "PNG";
        }

        if (data[6] == 'J' && data[7] == 'F' && data[8] == 'I'
                && data[9] == 'F') {
            return "JPG";
        }
        return null;
    }

    public static String byteToBase64(byte[] headshot) {
        String imgType = getImgType(headshot);
        if(imgType!=null)
            return String.format("data:image/%s;base64,", imgType)+ Base64.getEncoder().encodeToString(headshot);
        else
            return null;
    }
    public static ModelAndView createError(String status,String mes,String url){
        ModelAndView mav = new ModelAndView();
        mav.addObject("mes", String.format("%s<p class=\"page-mk\">%s</p>",status,mes));
        mav.addObject("from", url);
        mav.setViewName("error");
        return mav;
    }
}
