package com.tingyu.tongmeng.edu.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.edu.dao.TeacherMapper;
import com.tingyu.tongmeng.edu.service.edu.entity.Teacher;
import com.tingyu.tongmeng.edu.service.edu.service.TeacherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ??ʦ 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-25
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Cacheable(value = "teacherList",key = "'getTeacherList'")
    @Override
    public Map<String, Object> getTeacherList(Integer page, Integer limit) {

        Map<String,Object> result=new HashMap<>();

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        Page<Teacher> teacherPage=new Page<>(page,limit);
        baseMapper.selectPage(teacherPage,queryWrapper);
        long total = teacherPage.getTotal();
        List<Teacher> teacherList = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();
        result.put("total",total);
        result.put("teacherList",teacherList);
        result.put("current",current);
        result.put("pages",pages);
        result.put("hasNext",hasNext);
        result.put("hasPrevious",hasPrevious);
        return result;
    }
}
