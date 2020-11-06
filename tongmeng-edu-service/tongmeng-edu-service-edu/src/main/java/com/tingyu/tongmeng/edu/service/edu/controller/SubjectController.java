package com.tingyu.tongmeng.edu.service.edu.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.entity.Subject;
import com.tingyu.tongmeng.edu.service.edu.service.SubjectService;
import com.tingyu.tongmeng.edu.service.edu.vo.SubjectVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/edu/subject")

public class SubjectController {

    @Resource
    SubjectService subjectService;


    @GetMapping("list/tree")
    public R listByTree(){
        List<SubjectVo> subjectVos = subjectService.listByTree();
        return R.ok().data("rows", subjectVos);
    }

    @GetMapping("list")
    public R list(){
        List<Subject> list = subjectService.list();
        return R.ok().data("rows",list);
    }


    //添加课程分类
    @PostMapping("addSubject")
    public R save(MultipartFile file){
        subjectService.addSubject(file);
        return R.ok();
    }

}

