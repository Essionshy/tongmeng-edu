package com.tingyu.tongmeng.edu.service.sas.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.sas.service.StatisticsDailyService;
import com.tingyu.tongmeng.edu.service.sas.vo.QueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-01
 */
@RestController
@RequestMapping("/sas")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("list")
    public R get(@RequestBody QueryVo queryVo){

        Map<String,Object> map= statisticsDailyService.getDailyInfoCount(queryVo);
        return R.ok().data(map);
    }

    @ApiOperation("根据日期保存统计分析数据项")
    @PostMapping("count/save/{day}")
    public R createStatisticsByDay(@PathVariable("day")String day){
        statisticsDailyService.saveCountInfo(day);
        return R.ok();
    }


}

