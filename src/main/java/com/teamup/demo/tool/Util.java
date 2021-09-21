package com.teamup.demo.tool;

import java.text.SimpleDateFormat;
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
}
