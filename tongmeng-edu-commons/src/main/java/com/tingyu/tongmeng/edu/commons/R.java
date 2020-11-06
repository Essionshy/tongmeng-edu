package com.tingyu.tongmeng.edu.commons;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/25 19:35
 * @Version tongmeng-edu
 */

@Data
@Accessors(chain = true)
public class R  {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String,Object> data=new HashMap<>();

    /**
     * 构造方法私有化，不允许外界实例化
     */
    private R(){}

    public static R ok(){
        return new R().setSuccess(true).setCode(BizCode.SUCCESS.getCode()).setMessage(BizCode.SUCCESS.getMessage());
    }

    public static R error(){
        return new R().setSuccess(false).setCode(BizCode.ERROR.getCode()).setMessage(BizCode.ERROR.getMessage());
    }


    public R code(Integer code){
        this.code=code;
        return this;
    }

    public R message(String message){
        this.message=message;
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String ,Object> data){
        this.data=data;
        return this;
    }



}
