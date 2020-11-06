package com.tingyu.tongmeng.edu.service.rms.controller.front;

import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.rms.service.BannerService;
import com.tingyu.tongmeng.edu.service.rms.vo.BannerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/10/29 17:04
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("rms/front/banner")

public class BannerFrontController {

    @Autowired
    BannerService bannerService;

    //获取前两条数据

    @GetMapping("list")
    public R list(){
        List<BannerVo> bannerList = bannerService.getFrontBannerList();
        return R.ok().data("bannerList",bannerList);
    }

}
