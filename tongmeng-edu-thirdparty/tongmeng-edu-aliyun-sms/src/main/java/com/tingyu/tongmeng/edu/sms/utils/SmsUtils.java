package com.tingyu.tongmeng.edu.sms.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.tingyu.tongmeng.edu.sms.properties.AliyunSmsProperties;

import java.util.HashMap;

/**
 * @Author essionshy
 * @Create 2020/10/30 10:32
 * @Version tongmeng-edu
 */
public class SmsUtils {


    /**
     * 根据手机号发送手机短信验证码
     *
     * @param phone
     * @return
     */
    public static boolean sendMessage(String phone,String code) {


        //封装消息参数，因为阿里云短信需要json格式数据，方便转换
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);
        //

        DefaultProfile profile = DefaultProfile.getProfile("default",
                AliyunSmsProperties.ACCESS_KEY, AliyunSmsProperties.KEY_SECRET);

        //创建客户端
        IAcsClient client = new DefaultAcsClient(profile);
        //设置固定参数

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关参数

        request.putQueryParameter("PhoneNumbers", phone); //手机号
        request.putQueryParameter("TemplateCode", AliyunSmsProperties.TEMPLATE_CODE);//模板code
        request.putQueryParameter("SignName", AliyunSmsProperties.SIGN_NAME);//签名名称
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
