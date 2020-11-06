package com.tingyu.tongmeng.edu.commons;

import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/10/30 13:57
 * @Version tongmeng-edu
 */
@Data
public class ResultException extends RuntimeException{

    private int code;

    private String message;

    public ResultException(String message){
        super(message);
    }
    public ResultException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
