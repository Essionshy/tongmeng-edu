package com.tingyu.tongmeng.edu.service.member.service.wx.impl;

import com.tingyu.tongmeng.edu.service.member.properties.WXProperties;
import com.tingyu.tongmeng.edu.service.member.service.wx.WXApiService;
import com.tingyu.tongmeng.edu.service.member.utils.Constants;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * @Author essionshy
 * @Create 2020/10/30 17:36
 * @Version tongmeng-edu
 */
@Service
public class WXApiServiceImpl implements WXApiService {
    @Override
    public String login() {
        String url="";
        try{
            //redirect_url 进行编码

            String redirectUrl = URLEncoder.encode(WXProperties.REDIRECT_URL, "UTF-8");

            StringBuilder sb = new StringBuilder();
             url = sb.append(Constants.WX_LOGIN_URL)
                    .append("?appid=")
                    .append(WXProperties.APP_ID)
                    .append("&redirect_uri=")
                    .append(redirectUrl)
                    .append("&response_type=code&")
                     .append("scope=snsapi_login")
                    .append("&state=atguigu")
                    .append("#wechat_redirect").toString();

            System.out.println(url);

        }catch (Exception e){
          e.printStackTrace();
        }
        return url;
    }
}

