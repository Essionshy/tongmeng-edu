package com.tingyu.tongmeng.edu.service.edu.controller.front;

import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.entity.Course;
import com.tingyu.tongmeng.edu.service.edu.entity.Teacher;
import com.tingyu.tongmeng.edu.service.edu.service.CourseService;
import com.tingyu.tongmeng.edu.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/30 20:37
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("edu/teacher/front")

public class TeacherFrontController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @GetMapping("list/{page}/{limit}")
    public R list(@PathVariable("page")Integer page,
                  @PathVariable("limit")Integer limit){

        Map<String,Object> map=teacherService.getTeacherList(page,limit);

        return R.ok().data(map);
    }


    @GetMapping("get/{teacherId}")
    public R get(@PathVariable("teacherId")String teacherId){
        //查询讲师基本信息
        Teacher teacher = teacherService.getById(teacherId);

        //查询讲师讲授课程
        List<Course> courseList=courseService.getCourseListByTeacherId(teacherId);


        return R.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
