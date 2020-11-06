package com.tingyu.tongmeng.edu.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.commons.dto.OrderCourseDTO;
import com.tingyu.tongmeng.edu.service.edu.entity.Course;
import com.tingyu.tongmeng.edu.service.edu.param.CourseParam;
import com.tingyu.tongmeng.edu.service.edu.vo.CourseInfoVo;
import com.tingyu.tongmeng.edu.service.edu.vo.CoursePublishVo;
import com.tingyu.tongmeng.edu.service.edu.vo.front.CourseQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
public interface CourseService extends IService<Course> {

    /**
     * 保存课程基本信息并返回课程ID
     * @param courseInfoVo
     * @return
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String id);

    String updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishInfo(String courseId);

    boolean publish(String courseId);

    Map<String,Object> listByPage(Integer page, Integer limit, CourseParam courseParam);

    boolean delete(String courseId);

    List<Course> getCourseListByTeacherId(String teacherId);

    Map<String, Object> getCourseListByPage(Page<Course> coursePage, CourseQueryVo courseQueryVo);

    Map<String, Object> getCourseFrontInfoByCourseId(String courseId,String memberId);

    /**
     * 远程调用返回课程信息对象
     * @param courseId
     * @return
     */
    OrderCourseDTO getOrderCourse(String courseId);
}
