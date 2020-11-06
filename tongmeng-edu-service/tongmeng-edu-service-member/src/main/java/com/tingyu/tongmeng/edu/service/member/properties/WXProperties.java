package com.tingyu.tongmeng.edu.service.member.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/30 17:14
 * @Version tongmeng-edu
 */
@Component
public class WXProperties implements InitializingBean {

    @Value("${wx.open.app_id}")
    private String appId;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String redirectUrl;


    public static  String APP_ID;
    public static  String APP_SECRET;
    public static  String REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID=this.appId;
        APP_SECRET=this.appSecret;
        REDIRECT_URL=this.redirectUrl;
    }
}
