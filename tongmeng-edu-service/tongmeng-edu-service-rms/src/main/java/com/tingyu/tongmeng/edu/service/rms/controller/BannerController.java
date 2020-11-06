package com.tingyu.tongmeng.edu.service.rms.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.rms.entity.Banner;
import com.tingyu.tongmeng.edu.service.rms.param.BannerParam;
import com.tingyu.tongmeng.edu.service.rms.service.BannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/rms/banner")

public class BannerController {

    @Autowired
    BannerService bannerService;
    @ApiOperation("分页条件查询")
    @PostMapping("list/{page}/{limit}")
    public R list(
            @PathVariable("page") Integer page,
            @PathVariable("limit")Integer limit,
            @RequestBody BannerParam param){

        Map<String,Object> map = bannerService.listByPage(page, limit, param);
        return R.ok().data(map);
    }

    @PostMapping("save")
    public R save(@RequestBody BannerParam param){


        if(  bannerService.saveBanner(param)){
            return R.ok();
        }else{
            return R.error();
        }

    }


    @PutMapping("update")
    public R update(@RequestBody BannerParam param){


        if(  bannerService.updateBanner(param)){
            return R.ok();
        }else{
            return R.error();
        }

    }

    @DeleteMapping("delete/{bannerId}")
    public R delete(@PathVariable("bannerId") String  bannerId){


        if(  bannerService.removeById(bannerId)){
            return R.ok();
        }else{
            return R.error();
        }

    }

    @GetMapping("get/{bannerId}")
    public R get(@PathVariable("bannerId") String  bannerId){

        Banner banner = bannerService.getById(bannerId);

     return R.ok().data("banner",banner);

    }


}

