package com.tingyu.tongmeng.edu.service.edu.vo.front;

import com.tingyu.tongmeng.edu.service.edu.entity.Course;
import com.tingyu.tongmeng.edu.service.edu.entity.CourseDescription;
import com.tingyu.tongmeng.edu.service.edu.entity.Subject;
import com.tingyu.tongmeng.edu.service.edu.entity.Teacher;
import com.tingyu.tongmeng.edu.service.edu.vo.ChapterVo;
import lombok.Data;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/10/31 0:19
 * @Version tongmeng-edu
 */
@Data
public class CourseFrontInfoVo {

    private Course course;

    private CourseDescription courseDescription;

    private Teacher teacher;

    private Subject firstLevelSubject;

    private Subject secondLevelSubject;

    private List<ChapterVo> chapters;

}
