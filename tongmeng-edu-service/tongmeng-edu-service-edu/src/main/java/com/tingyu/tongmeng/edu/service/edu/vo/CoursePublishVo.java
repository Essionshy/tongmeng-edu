package com.tingyu.tongmeng.edu.service.edu.vo;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 课程发布信息确认Vo
 * @Author essionshy
 * @Create 2020/10/28 15:34
 * @Version tongmeng-edu
 */

@Data
public class CoursePublishVo {
    private String id;

    //讲师名称
    private String teacherName;

    //二级分类标题
    private String subjectTitle;

    //一级分类标题
    private String subjectParentTitle;

    //课程标题
    private String title;

    //课程价格
    private BigDecimal price;

    //课程总课时数
    private Integer lessonNum;

    //课程封面
    private String cover;

    //课程简介
    private String description;


}
