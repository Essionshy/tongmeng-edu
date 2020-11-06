package com.tignyu.tongmeng.edu.service.member.test;

import com.tingyu.tongmeng.edu.commons.JwtUtils;
import com.tingyu.tongmeng.edu.commons.MD5Utils;

/**
 * @Author essionshy
 * @Create 2020/10/30 18:14
 * @Version tongmeng-edu
 */
public class Test {
    public static void main(String[] args) {

        String token = JwtUtils.getToken("1106831936900415490", "文若姬");
        System.out.println(token);

        String encrypt = MD5Utils.encrypt("123456");
        System.out.println(encrypt);

    }
}
