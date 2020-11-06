package com.tingyu.tongmeng.edu.sms.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @Author essionshy
 * @Create 2020/10/30 11:14
 * @Version tongmeng-edu
 */
public class RandomCodeGeneratorUtils {
    private RandomCodeGeneratorUtils(){}


private static final Random RANDOM=new Random();
    private static final DecimalFormat DF_FOUR=new DecimalFormat("0000");
    private static final DecimalFormat DF_SIX=new DecimalFormat("000000");

    /**
     * 随机生成4位数据验证码
     * @return
     */
    public static String getFourBitCode(){
       return DF_FOUR.format(RANDOM.nextInt(10000));
    }

    /**
     * 随机生成6位数据验证码
     * @return
     */
    public static String getSixBitCode(){
        return DF_FOUR.format(RANDOM.nextInt(1000000));
    }
}
