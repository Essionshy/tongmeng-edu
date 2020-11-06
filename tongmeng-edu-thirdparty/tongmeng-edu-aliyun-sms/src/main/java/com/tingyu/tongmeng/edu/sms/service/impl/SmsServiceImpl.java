package com.tingyu.tongmeng.edu.sms.service.impl;

import com.tingyu.tongmeng.edu.sms.service.SmsService;
import com.tingyu.tongmeng.edu.sms.utils.Constants;
import com.tingyu.tongmeng.edu.sms.utils.RandomCodeGeneratorUtils;
import com.tingyu.tongmeng.edu.sms.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author essionshy
 * @Create 2020/10/30 10:30
 * @Version tongmeng-edu
 */
@Service
public class SmsServiceImpl implements SmsService {



    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean send(String phone) {
        //1.生成随机验证码
        String code = RandomCodeGeneratorUtils.getSixBitCode();
        System.out.println(code);


        //2.将验证码保存到Redis中并设置过期时间为60*5秒
        stringRedisTemplate.opsForValue().set(phone,code, Constants.VERIFY_CODE_EXPIRE, TimeUnit.SECONDS);
        //3.调用阿里云短信服务
        boolean isSuccess = SmsUtils.sendMessage(phone, code);
        return isSuccess;
    }
}
