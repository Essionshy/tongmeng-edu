package com.tingyu.tongmeng.edu.service.order.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Author essionshy
 * @Create 2020/10/31 16:43
 * @Version tongmeng-edu
 */
public final class OrderNoGeneratorUtils {

    static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
    static DecimalFormat df=new DecimalFormat("000000");


    public static String  get(){

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成20位数字的字符串 格式 yyyyMMddHH:mm:ss+ random.next
     * @return
     */
    public static String create(){
        String result="";

        Date date = new Date();

        String format = sdf.format(date);
        String num = df.format(new Random().nextInt(1000000));
        result=format+num;

        return result;
    }

    public static void main(String[] args) {
        String s = create();
        System.out.println(s);
    }

}
