package com.tingyu.tongmeng.edu.vod.controller;

import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author essionshy
 * @Create 2020/10/28 19:18
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("vod/video")
@CrossOrigin
public class VodController {

    @Autowired
    VodService vodService;

    @PostMapping("upload")
    public R upload(MultipartFile file){

        String videoId = vodService.upload(file);

        return R.ok().data("videoId",videoId);
    }

    /**
     * 根据视频ID删除阿里云视频点播中的视频文件
     * @param videoId
     * @return
     */
    @DeleteMapping("delete/{videoId}")
    public R delete(@PathVariable("videoId") String videoId){
        System.out.println("Vod 删除视频服务 videoId"+videoId);
        vodService.delete(videoId);
        return R.ok();
    }

    @ApiOperation("根据视频ID获取视频的播放凭证")
    @GetMapping("get/auth/{vid}")
    public R getAuth(@PathVariable("vid")String vid){

        String auth = vodService.getAuth(vid);
        return R.ok().data("auth",auth);
    }

}
