package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.tingyu.tongmeng.edu.commons.ResultException;
import com.tingyu.tongmeng.edu.security.entity.LoginUser;
import com.tingyu.tongmeng.edu.security.entity.SecurityUser;
import com.tingyu.tongmeng.edu.service.acl.service.PermissionService;
import com.tingyu.tongmeng.edu.service.acl.service.UserService;
import com.tingyu.tongmeng.edu.service.acl.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/2 21:52
 * @Version tongmeng-edu
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        UserVo user = userService.getByUsername(username);
        // 判断用户是否存在
        if (null == user){
            throw new ResultException(3000,"用户不存在");
        }
        // 返回UserDetails实现类
        LoginUser currentUser = new LoginUser();
        BeanUtils.copyProperties(user,currentUser);

        List<String> authorities = permissionService.listPermissionValueByUserId(user.getId());
        //当前登录用户
        SecurityUser securityUser = new SecurityUser(currentUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }
}
