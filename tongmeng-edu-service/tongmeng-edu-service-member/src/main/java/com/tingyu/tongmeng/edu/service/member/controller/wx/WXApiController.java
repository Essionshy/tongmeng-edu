package com.tingyu.tongmeng.edu.service.member.controller.wx;

import com.tingyu.tongmeng.edu.service.member.service.wx.WXApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author essionshy
 * @Create 2020/10/30 17:33
 * @Version tongmeng-edu
 */
@Controller
@RequestMapping("/api/member/wx")
public class WXApiController {

    @Autowired
    WXApiService wxApiService;

    /**
     * 微信登录页面
     * @return
     */
    @GetMapping("login")
    public String login(){
        String url=wxApiService.login();
        return "redirect:"+url;
    }

}
