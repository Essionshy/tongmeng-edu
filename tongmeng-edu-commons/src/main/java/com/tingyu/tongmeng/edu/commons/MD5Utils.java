package com.tingyu.tongmeng.edu.commons;

import java.security.MessageDigest;

/**
 * @Author essionshy
 * @Create 2020/10/30 14:01
 * @Version tongmeng-edu
 */
public class MD5Utils {
    private MD5Utils() {
    }

    /**
     * MD5加密
     *
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        try {

            char hexChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte[] bytes = password.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }

            return new String(chars);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(200, "MD5加密错误" + e.getMessage());
        }


    }

}
