package com.teamup.demo.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    /*将Date转换为String*/
    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
}
