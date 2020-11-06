package com.tingyu.tongmeng.edu.service.edu.service.front.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tingyu.tongmeng.edu.service.edu.entity.Course;
import com.tingyu.tongmeng.edu.service.edu.entity.Teacher;
import com.tingyu.tongmeng.edu.service.edu.service.CourseService;
import com.tingyu.tongmeng.edu.service.edu.service.TeacherService;
import com.tingyu.tongmeng.edu.service.edu.service.front.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/29 16:21
 * @Version tongmeng-edu
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;


    @Cacheable(value = "index",key = "'listCoursesAndTeachers'")
    @Override
    public Map<String, Object> listCoursesAndTeachers() {
        Map<String,Object> result=new HashMap<>();
        //1.查询浏览量排名前8的课程
        List<Course> courseList = getCourseList();
        result.put("courseList",courseList);
        //2.查询讲师ID倒序前4位讲师

        List<Teacher> teacherList = getTeacherList();
        result.put("teacherList",teacherList);
        return result;

    }

    @Cacheable(value = "teacher",key = "'getTeacherList'")
    public List<Teacher> getTeacherList() {
        QueryWrapper<Teacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        return teacherService.list(teacherWrapper);
    }

    @Cacheable(value = "course",key = "'getCourseList'")
    public List<Course> getCourseList() {
        QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("view_count");
        courseWrapper.last("limit 8");
        return courseService.list(courseWrapper);
    }
}
