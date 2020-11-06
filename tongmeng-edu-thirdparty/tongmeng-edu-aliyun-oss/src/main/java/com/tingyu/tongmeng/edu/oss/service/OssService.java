package com.tingyu.tongmeng.edu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author essionshy
 * @Create 2020/10/27 10:19
 * @Version tongmeng-edu
 */
public interface OssService {
    //返回上传文件后的路径
    String uploadFileAvatar(MultipartFile file);
}
