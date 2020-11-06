package com.tingyu.tongmeng.edu.service.edu.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.commons.dto.OrderCourseDTO;
import com.tingyu.tongmeng.edu.service.edu.param.CourseParam;
import com.tingyu.tongmeng.edu.service.edu.service.CourseService;
import com.tingyu.tongmeng.edu.service.edu.vo.CourseInfoVo;
import com.tingyu.tongmeng.edu.service.edu.vo.CoursePublishVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/edu/course")

public class CourseController {

    @Autowired
    CourseService courseService;


    @PostMapping("list/{page}/{limit}")
    public R list(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            @RequestBody CourseParam courseParam){

        Map<String ,Object> map = courseService.listByPage(page, limit, courseParam);


        return R.ok().data(map);
    }

    @PostMapping("save")
    public R save(@RequestBody CourseInfoVo courseInfoVo) {
        String courseId = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", courseId);

    }

    @PutMapping("update")
    public R update(@RequestBody CourseInfoVo courseInfoVo) {
        String courseId = courseService.updateCourseInfo(courseInfoVo);
        return R.ok().data("courseId", courseId);

    }


    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CourseInfoVo courseInfo = courseService.getCourseInfo(id);
        return R.ok().data("courseInfo", courseInfo);
    }

    @GetMapping("get/publish/{courseId}")
    public R getCoursePublishInfo(@PathVariable("courseId") String courseId){

        CoursePublishVo coursePublishInfo = courseService.getCoursePublishInfo(courseId);
        return R.ok().data("coursePublishInfo",coursePublishInfo);
    }

    //发布课程
    @PutMapping("/publish/{courseId}")
    public R publish(@PathVariable("courseId")String courseId){

        if(courseService.publish(courseId)){

            return R.ok();
        }else{
            return R.error();
        }
    }

    //删除课程，级联删除该课程下的章节，简介，小节，视频
    @DeleteMapping("/delete/{courseId}")
    public R delete(@PathVariable("courseId")String courseId){

        if(courseService.delete(courseId)){

            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("remote/get/{courseId}")
    public OrderCourseDTO getOrderCourseDTO(@PathVariable("courseId")String courseId){
        return courseService.getOrderCourse(courseId);
    }

}

