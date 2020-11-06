package com.tingyu.tongmeng.edu.sms.controller;

import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.sms.service.SmsService;
import com.tingyu.tongmeng.edu.sms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author essionshy
 * @Create 2020/10/30 10:27
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("sms")
@CrossOrigin
public class SmsController {
    @Autowired
    SmsService smsService;

    @GetMapping("send/{phone}")
    public R send(@PathVariable("phone") String phone){
        boolean isSuccess = smsService.send(phone);

        if(isSuccess){
            return R.ok().message(Constants.MESSAGE_SEND_SUCCESS);
        }else{
            return R.error().message(Constants.MESSAGE_SEND_FAILED);
        }


    }

}
