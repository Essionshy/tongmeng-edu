package com.tingyu.tongmeng.edu.vod.service.impl;

import com.tingyu.tongmeng.edu.vod.service.VodService;
import com.tingyu.tongmeng.edu.vod.utils.VideoUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author essionshy
 * @Create 2020/10/28 19:19
 * @Version tongmeng-edu
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String upload(MultipartFile file) {

        return VideoUtils.uploadVideo(file);
    }

    @Override
    public boolean delete(String videoId) {

        String delete = VideoUtils.delete(videoId);
        if(!"".equals(delete)){
            return true;
        }
        return false;
    }

    @Override
    public String getAuth(String vid) {

        String auth = VideoUtils.getAuth(vid);
        return auth;
    }
}
