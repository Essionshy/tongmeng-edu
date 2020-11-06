package com.tingyu.tongmeng.edu.service.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tingyu.tongmeng.edu.commons.JwtUtils;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.entity.Course;
import com.tingyu.tongmeng.edu.service.edu.service.CourseService;
import com.tingyu.tongmeng.edu.service.edu.vo.front.CourseQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/30 20:37
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("edu/course/front")

public class CourseFrontController {



    @Autowired
    CourseService courseService;

    @PostMapping("list/{page}/{limit}")
    public R list(@PathVariable("page")Integer page,
                  @PathVariable("limit")Integer limit,
                  @RequestBody(required = false) CourseQueryVo courseQueryVo ){

        Page<Course> coursePage = new Page<>(page, limit);
        Map<String,Object> map=courseService.getCourseListByPage(coursePage,courseQueryVo);
        return R.ok().data(map);
    }

    @GetMapping("get/{courseId}")
    public R get(@PathVariable("courseId")String courseId, HttpServletRequest request){
        Map<String ,Object> map=courseService.getCourseFrontInfoByCourseId(courseId, JwtUtils.getUserIdByToken(request));
        return R.ok().data(map);
    }


}
