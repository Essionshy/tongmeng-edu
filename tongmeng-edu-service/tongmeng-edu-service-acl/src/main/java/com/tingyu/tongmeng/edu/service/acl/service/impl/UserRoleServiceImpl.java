package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.acl.dao.UserRoleMapper;
import com.tingyu.tongmeng.edu.service.acl.entity.UserRole;
import com.tingyu.tongmeng.edu.service.acl.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<String> listRoleIdsByUserId(String userId) {
        List<String> roleIds=new ArrayList<>();
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.select("role_id");
        List<UserRole> userRoleList = baseMapper.selectList(wrapper);
        userRoleList.stream().forEach(userRole -> {
            roleIds.add(userRole.getRoleId());

        });
        return roleIds;
    }
}
