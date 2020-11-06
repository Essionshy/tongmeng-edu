package com.tingyu.tongmeng.edu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author essionshy
 * @Create 2020/10/28 19:19
 * @Version tongmeng-edu
 */
public interface VodService {
    //上传视频文件成功后返回视频文件ID
    String upload(MultipartFile file);

    boolean delete(String videoId);

    String getAuth(String vid);
}
