package com.tingyu.tongmeng.edu.oss.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/27 10:09
 * @Version tongmeng-edu
 */

@Component
public class AliyunOssProperties implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.key}")
    private String accessKey;

    @Value("${aliyun.oss.file.key.secret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucket.name}")
    private String bucketName;



    //定义公共访问常量

    public static String END_POINT;
    public static String ACCESS_KEY;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=this.endpoint;
        ACCESS_KEY=this.accessKey;
        KEY_SECRET=this.keySecret;
        BUCKET_NAME=this.bucketName;
    }



}
