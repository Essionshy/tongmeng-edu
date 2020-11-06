package com.tingyu.tongmeng.edu.service.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.entity.Teacher;
import com.tingyu.tongmeng.edu.service.edu.param.TeacherParam;
import com.tingyu.tongmeng.edu.service.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ??ʦ 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-25
 */
@RestController
@RequestMapping("/edu/teacher")
@Slf4j

public class TeacherController {

    @Resource
    TeacherService teacherService;

    @ApiOperation("条件分页查询讲师列表")
    @PostMapping("list/{page}/{limit}")
    public R list(@PathVariable Integer page, //由于前端分页控件绑定的数据没有放入queryParam对象中，单独传值
                  @PathVariable Integer limit,
                  @RequestBody TeacherParam param) {

        log.info("条件参数：【{}】", param);

        Map<String, Object> result = new HashMap<>();
        //构建QueryWrapper对象封装查询条件
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        if(param.getName()!=null ||!"".equals(param.getName())){
            queryWrapper.like("name", param.getName());
        }
        if(param.getLevel()!=null){
            queryWrapper.eq("level",param.getLevel());
        }
        //设置分页参数
        Page<Teacher> pageTeacher = new Page<>(page, limit);
        teacherService.page(pageTeacher, queryWrapper); //调用分页查询方法
        long total = pageTeacher.getTotal();
        List<Teacher> teachers = pageTeacher.getRecords();
        result.put("total", total);
        result.put("rows", teachers);
        return R.ok().data(result);
    }

    @GetMapping("list")
    public R list(){

        List<Teacher> teachers = teacherService.list();

        return R.ok().data("teachers",teachers);
    }


    @PutMapping("update")
    public R update(@RequestBody Teacher teacher){
        if(teacherService.updateById(teacher)){
            return R.ok();
        }else{
            return R.error();
        }

    }


    @ApiOperation("添加讲师")
    @PostMapping("save")
    public R save(@RequestBody Teacher teacher) {
        log.info("添加讲师{}", teacher);
        teacherService.save(teacher);
        return R.ok();
    }
    @ApiOperation("删除讲师")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id){

        if(teacherService.removeById(id)){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation("根据ID查询讲师")
    @GetMapping("/{id}")
    public R get(@PathVariable String id){

        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }


}

