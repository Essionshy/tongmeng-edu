package com.tingyu.tongmeng.edu.commons;

/**
 * @Author essionshy
 * @Create 2020/10/25 21:09
 * @Version tongmeng-edu
 */
public enum BizCode {

    SUCCESS(20000,"成功"),
    ERROR(500,"错误");

    private Integer code;

    private String message;

    BizCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
