package com.tingyu.tongmeng.edu.service.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.acl.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/11/3 10:52
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("admin/sys")
@Slf4j
public class AdminIndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("info")
    public R getLoginUserInfo(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("login user username:{}",username);
        //返回登录用户的 基本信息，角色信息，权限菜单信息
        Map<String,Object> map=indexService.getLoginUserInfo(username);
        return R.ok().data(map);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public R getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return R.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
