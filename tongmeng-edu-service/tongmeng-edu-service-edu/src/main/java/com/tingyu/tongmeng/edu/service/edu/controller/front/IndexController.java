package com.tingyu.tongmeng.edu.service.edu.controller.front;

import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.service.front.IndexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/29 16:17
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("edu/front/index")

public class IndexController {

    @Autowired
    IndexService indexService;

    @ApiOperation("获得前台首页8门热门课程与4名名师")
    @GetMapping("list")
    public R index(){

        Map<String, Object> map = indexService.listCoursesAndTeachers();
        return R.ok().data(map);
    }

}
