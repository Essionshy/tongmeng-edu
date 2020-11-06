package com.tingyu.tongmeng.edu.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/1 21:54
 * @Version tongmeng-edu
 */
public final class DateUtils {

    static final String dash_pattern="yyyy-MM-dd";

    static final SimpleDateFormat sdf=new SimpleDateFormat(dash_pattern);
    public static String now(){


        return  sdf.format(new Date());
    }



    public static String formatLastDay(){

        return sdf.format(addDays(new Date(),-1));
    }


    public static Date addDays(Date date,int amount){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+amount);
        return now.getTime();
    }


    public static void main(String[] args) {
        Date date = addDays(new Date(), -1);
        String now = sdf.format(date);
        System.out.println(now);
    }
}
