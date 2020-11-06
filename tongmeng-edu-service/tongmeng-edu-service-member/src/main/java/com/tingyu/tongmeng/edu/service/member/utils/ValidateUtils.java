package com.tingyu.tongmeng.edu.service.member.utils;

import com.tingyu.tongmeng.edu.commons.ResultException;
import com.tingyu.tongmeng.edu.service.member.vo.LoginVo;
import com.tingyu.tongmeng.edu.service.member.vo.RegisterVo;
import org.springframework.util.StringUtils;

/**
 * @Author essionshy
 * @Create 2020/10/30 15:36
 * @Version tongmeng-edu
 */
public final class ValidateUtils {

    /**
     * 登录校验，密码是否为空     *
     * @param loginVo
     */
    public static void validateLogin(LoginVo loginVo){
        if(StringUtils.isEmpty(loginVo.getMobile())){
            throw new ResultException(200,"手机号不能为空");
        }

        if(StringUtils.isEmpty(loginVo.getPassword())){
            throw new ResultException(200,"密码不能为空");
        }
    }

    /**
     * 会员注册校验
     *
     * @param registerVo
     */
    public static void validateRegister(RegisterVo registerVo){

        if(StringUtils.isEmpty(registerVo.getNickname())){
            throw new ResultException(200,"用户昵称不能为空");
        }

        if(StringUtils.isEmpty(registerVo.getMobile())){
            throw new ResultException(200,"手机号不能为空");
        }


        if(StringUtils.isEmpty(registerVo.getPassword())){
            throw new ResultException(200,"密码不能为空");
        }

        if(StringUtils.isEmpty(registerVo.getCode())){
            throw new ResultException(200,"验证码不能为空");
        }

    }

}
