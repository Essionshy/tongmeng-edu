package com.tingyu.tongmeng.edu.service.acl.utils;

import com.tingyu.tongmeng.edu.service.acl.entity.Role;
import com.tingyu.tongmeng.edu.service.acl.vo.RoleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2020/11/9 14:54
 * @Version tongmeng-edu
 */
public class RoleUtil {

    public static List<RoleVo> convertFromRole(List<Role> roles) {

        if (roles.isEmpty()) {
            return null;
        } else {
            return roles.stream().map(role -> {
                RoleVo roleVo = new RoleVo();
                BeanUtils.copyProperties(role, roleVo);
                return roleVo;
            }).collect(Collectors.toList());
        }

    }

    public static RoleVo convertFromRole(Role role) {
        if (role == null) {
            return null;
        }

        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role, roleVo);
        return roleVo;

    }

}
