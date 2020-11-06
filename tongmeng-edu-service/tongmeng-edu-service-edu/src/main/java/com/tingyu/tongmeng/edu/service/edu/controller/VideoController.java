package com.tingyu.tongmeng.edu.service.edu.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.entity.Video;
import com.tingyu.tongmeng.edu.service.edu.param.VideoParam;
import com.tingyu.tongmeng.edu.service.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("save")
    public R save(@RequestBody VideoParam param){

        boolean flag = videoService.save(param);

        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PutMapping("update")
    public R update(@RequestBody VideoParam param){

        boolean flag = videoService.update(param);

        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @DeleteMapping("delete/{videoId}")
    public R delete(@PathVariable String videoId){

        boolean flag=videoService.removeById(videoId);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("get/{videoId}")
    public R get(@PathVariable String videoId){

        Video video = videoService.getById(videoId);
        return R.ok().data("video",video);
    }

}

