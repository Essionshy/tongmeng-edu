package com.tingyu.tongmeng.edu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tingyu.tongmeng.edu.oss.properties.AliyunOssProperties;
import com.tingyu.tongmeng.edu.oss.service.OssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author essionshy
 * @Create 2020/10/27 10:20
 * @Version tongmeng-edu
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //获取Oss存储属性
        String endpoint= AliyunOssProperties.END_POINT;
        String accessKey= AliyunOssProperties.ACCESS_KEY;
        String keySecret= AliyunOssProperties.KEY_SECRET;
        String bucketName= AliyunOssProperties.BUCKET_NAME;
        String url="";
        try {
            //构造OSS客户端
            OSS oss = new OSSClientBuilder().build(endpoint, accessKey, keySecret);
            //上传文件流
            InputStream is=file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            //为了避免不同的人上传相同的文件名会造成文件覆盖，进行以下处理
            //1.给文件名加上一个随机字符串
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String newFilename=uuid+originalFilename;

            //2.按照日期进行文件的分类管理 2020/10/11/xxx.jpg

            String datePath=new DateTime().toString("yyyy/MM/dd");//格式化显示日期

            String filePath=datePath+"/"+newFilename;

            oss.putObject(bucketName,filePath,is);
            //手动拼接url

            StringBuilder sb = new StringBuilder();
            sb.append("https://")
                    .append(bucketName)
                    .append(".")
                    .append(endpoint)
                    .append("/")
                    .append(filePath);

            url=sb.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return url;
    }
}
