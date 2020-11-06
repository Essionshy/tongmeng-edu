package com.tingyu.tongmeng.edu.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.commons.dto.OrderCourseDTO;
import com.tingyu.tongmeng.edu.service.edu.dao.CourseMapper;
import com.tingyu.tongmeng.edu.service.edu.entity.*;
import com.tingyu.tongmeng.edu.service.edu.enums.PublishStatus;
import com.tingyu.tongmeng.edu.service.edu.param.CourseParam;
import com.tingyu.tongmeng.edu.service.edu.remote.OrderFeignService;
import com.tingyu.tongmeng.edu.service.edu.remote.VodFeignService;
import com.tingyu.tongmeng.edu.service.edu.service.*;
import com.tingyu.tongmeng.edu.service.edu.vo.ChapterVo;
import com.tingyu.tongmeng.edu.service.edu.vo.CourseInfoVo;
import com.tingyu.tongmeng.edu.service.edu.vo.CoursePublishVo;
import com.tingyu.tongmeng.edu.service.edu.vo.front.CourseQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseDescriptionService courseDescriptionService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    VideoService videoService;

    @Autowired
    VodFeignService vodFeignService;


    @Autowired
    OrderFeignService orderFeignService;
    /**
     * 课程基本信息与课程描述信息为一对一关系，因此id相同，在同一事务中，要么同时成功，要么同时失败
     *
     * @param courseInfoVo
     * @return
     */
    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.保存课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int count = courseMapper.insert(course);
        String courseId = course.getId();
        //2.保存课程描述信息

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(courseId);
        boolean save = courseDescriptionService.save(courseDescription);

        return courseId;
    }

    @Override
    public CourseInfoVo getCourseInfo(String id) {
        //1、获取课程基本信息
        CourseInfoVo courseInfo = new CourseInfoVo();
        Course course = courseMapper.selectById(id);
        if (course != null) {
            BeanUtils.copyProperties(course, courseInfo);
        }
        //2.获取课程描述信息
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        if (courseDescription != null) {
            courseInfo.setDescription(courseDescription.getDescription());
        }
        return courseInfo;
    }

    @Override
    public String updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.更新课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int count = courseMapper.updateById(course);


        String courseId = courseInfoVo.getId();
        //2.保存课程描述信息

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(courseInfoVo.getId());

        boolean isUpdated = courseDescriptionService.updateById(courseDescription);


        return courseId;
    }

    @Override
    public CoursePublishVo getCoursePublishInfo(String courseId) {
        return courseMapper.getCoursePublishInfo(courseId);
    }

    @Override
    public boolean publish(String courseId) {
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(PublishStatus.NORMAL);
        int count = courseMapper.updateById(course);
        return count > 0;
    }

    @Override
    public Map<String, Object> listByPage(Integer page, Integer limit, CourseParam courseParam) {

        Map<String, Object> result = new HashMap<>();
        //1.封装查询对象
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        if (courseParam.getStatus() != null && "".equals(courseParam.getTitle())) {
            wrapper.eq("status", courseParam.getStatus());
        }
        if (courseParam.getTitle() != null && "".equals(courseParam.getTitle()))
            wrapper.like("title", courseParam.getTitle());
        //2.封装分页对象
        Page<Course> coursePage = new Page<>(page, limit);
        //调用分页查询
        courseMapper.selectPage(coursePage, wrapper);
        long total = coursePage.getTotal();
        List<Course> records = coursePage.getRecords();
        result.put("total", total);
        result.put("courseList", records);

        return result;
    }

    @Override
    public boolean delete(String courseId) {

        //5.删除视频文件,通过OpenFeign调用Vod服务; 首先执行，否则小节删除后，无法获取视频 ID
        List<Video> videos = videoService.getVideosByCourseId(courseId);
        //遍历删除
        videos.forEach(video -> {
            //远程调用删除
            vodFeignService.delete(video.getVideoSourceId());
        });
        //1.删除课程基本信息
        int courseCount = courseMapper.deleteById(courseId);
        //2.删除课程描述信息
        boolean isDescriptionDeleted = courseDescriptionService.removeById(courseId);
        //3.删除课程章节信息
        boolean isChapterDeleted = chapterService.deleteByCourseId(courseId);
        //4.删除小节信息
        boolean isVideoDeleted = videoService.deleteByCourseId(courseId);



        boolean flag = courseCount > 0 && isChapterDeleted && isChapterDeleted && isVideoDeleted && isDescriptionDeleted;
        return flag;
    }

    @Override
    public List<Course> getCourseListByTeacherId(String teacherId) {

        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        wrapper.eq("teacher_id",teacherId);
        List<Course> courseList = courseMapper.selectList(wrapper);
        return courseList;
    }

    @Override
    public Map<String, Object> getCourseListByPage(Page<Course> coursePage, CourseQueryVo courseQueryVo) {

        Map<String,Object> result=new HashMap<>();
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        String subjectParentId = courseQueryVo.getSubjectParentId(); //一级分类
        String subjectId = courseQueryVo.getSubjectId();//二级分类
        String buyCount = courseQueryVo.getBuyCountSort();//购买量 关注度
        String gmtCreateSort = courseQueryVo.getGmtCreateSort(); //时间排序
        String priceSort = courseQueryVo.getPriceSort();
        //判断
        if(!StringUtils.isEmpty(subjectId)){
            wrapper.eq("subject_id",subjectId);
        }

        if(!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }

        if(!StringUtils.isEmpty(buyCount)){
            wrapper.orderByDesc("buy_count");
        }

        if(!StringUtils.isEmpty(gmtCreateSort)){
            wrapper.orderByDesc("gmt_create");
        }

        if(!StringUtils.isEmpty(priceSort)){
            wrapper.orderByDesc("price");
        }

        courseMapper.selectPage(coursePage,wrapper);

        long total = coursePage.getTotal();
        List<Course> courseList = coursePage.getRecords();
        long current = coursePage.getCurrent();
        long pages = coursePage.getPages();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();
        result.put("total",total);
        result.put("courseList",courseList);
        result.put("current",current);
        result.put("pages",pages);
        result.put("hasNext",hasNext);
        result.put("hasPrevious",hasPrevious);
        return result;
    }

    @Override
    public Map<String,Object> getCourseFrontInfoByCourseId(String courseId,String memberId) {

        Map<String,Object> map=new HashMap<>();

        Course course = courseMapper.selectById(courseId);
        map.put("course",course);
        //查询课程描述

        CourseDescription description = courseDescriptionService.getById(courseId);
        map.put("description",description);

        if(course!=null){

            //查询
            Subject firstLevelSubject = subjectService.getById(course.getSubjectParentId());
            map.put("firstLevelSubject",firstLevelSubject);
            Subject secondLevelSubject = subjectService.getById(course.getSubjectId());
            map.put("secondLevelSubject",secondLevelSubject);
            //查询讲师

            Teacher teacher = teacherService.getById(course.getTeacherId());
            map.put("teacher",teacher);


        }
        //查询该用户是否已经购买该课程
        boolean isBuy = orderFeignService.isBuy(courseId, memberId);

        map.put("isBuy",isBuy);

        //查询章节
        List<ChapterVo> chapterList = chapterService.listChaptersByCourseId(courseId);
        map.put("chapterList",chapterList);
        return map;
    }

    @Override
    public OrderCourseDTO getOrderCourse(String courseId) {
        OrderCourseDTO orderCourseDTO = new OrderCourseDTO();

        Course course = baseMapper.selectById(courseId);
        if(course!=null){
            BeanUtils.copyProperties(course,orderCourseDTO);
            //根据
            Teacher teacher = teacherService.getById(course.getTeacherId());
            if(teacher!=null){
                orderCourseDTO.setTeacherName(teacher.getName());
            }
        }

        return orderCourseDTO;
    }
}
