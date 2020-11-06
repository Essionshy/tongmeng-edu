package com.tingyu.tongmeng.edu.service.edu.dao;

import com.tingyu.tongmeng.edu.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tingyu.tongmeng.edu.service.edu.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
public interface CourseMapper extends BaseMapper<Course> {

    //根据课程ID获取课程发布信息
    CoursePublishVo getCoursePublishInfo(@Param("courseId") String courseId);
}
