package com.tingyu.tongmeng.edu.vod.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/28 17:54
 * @Version tongmeng-edu
 */
@Component
public class AliyunVodProperties implements InitializingBean {

    @Value("${aliyun.vod.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.vod.file.key}")
    private String accessKey;

    @Value("${aliyun.vod.file.key.secret}")
    private String keySecret;


    //定义公共访问常量

    public static String END_POINT;
    public static String ACCESS_KEY;
    public static String KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=this.endpoint;
        ACCESS_KEY=this.accessKey;
        KEY_SECRET=this.keySecret;
    }
}
