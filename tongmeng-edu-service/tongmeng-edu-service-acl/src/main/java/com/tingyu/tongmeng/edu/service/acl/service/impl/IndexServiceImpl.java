package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.acl.service.IndexService;
import com.tingyu.tongmeng.edu.service.acl.service.PermissionService;
import com.tingyu.tongmeng.edu.service.acl.service.RoleService;
import com.tingyu.tongmeng.edu.service.acl.service.UserService;
import com.tingyu.tongmeng.edu.service.acl.utils.MenuBuilderUtil;
import com.tingyu.tongmeng.edu.service.acl.vo.PermissionVo;
import com.tingyu.tongmeng.edu.service.acl.vo.RoleVo;
import com.tingyu.tongmeng.edu.service.acl.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/11/3 17:21
 * @Version tongmeng-edu
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;
    @Override
    public Map<String, Object> getLoginUserInfo(String username) {
        Map<String, Object> map = new HashMap<>();
        //1.查询用户的基本信息
        UserVo user = userService.getByUsername(username);
        //2.查询用户的角色信息
        List<RoleVo> roleList = roleService.listByUserId(user.getId());
        //3.查询用户的权限信息
        List<PermissionVo> permissionList = permissionService.listPermissionListByUserId(user.getId());
        map.put("userInfo", user);
        map.put("roleList", roleList);
        map.put("permissionList", permissionList);
        return map;
    }

    @Override
    public List<JSONObject> getMenu(String username) {

        UserVo user = userService.getByUsername(username);

        List<PermissionVo> permissionVoList = permissionService.listPermissionListByUserId(user.getId());

        List<JSONObject> menu = MenuBuilderUtil.bulid(permissionVoList);

        return menu;
    }

    @PostMapping("logout")
    public R logout(){

        return R.ok();
    }

}
