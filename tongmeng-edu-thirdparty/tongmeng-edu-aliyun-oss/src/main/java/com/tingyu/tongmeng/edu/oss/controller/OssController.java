package com.tingyu.tongmeng.edu.oss.controller;

import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author essionshy
 * @Create 2020/10/27 10:20
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("/oss/file")
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    //上传文件
    @PostMapping("upload")
    public R upload(MultipartFile file){

        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }
}
