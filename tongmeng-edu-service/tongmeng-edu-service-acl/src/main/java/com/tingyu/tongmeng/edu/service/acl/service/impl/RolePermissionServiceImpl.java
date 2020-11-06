package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tingyu.tongmeng.edu.service.acl.entity.RolePermission;
import com.tingyu.tongmeng.edu.service.acl.dao.RolePermissionMapper;
import com.tingyu.tongmeng.edu.service.acl.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<RolePermission> getByRoleId(String id) {

        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",id);

        List<RolePermission> rolePermissions = baseMapper.selectList(wrapper);

        return rolePermissions;
    }

    /**
     * 根据角色id查询权限列表的id集合
     * @param roleId
     * @return
     */
    @Override
    public List<String> getPermissionIdsByRoleId(String roleId){

        List<String> permissionIds=new ArrayList<>();
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        wrapper.select("permission_id");


        List<RolePermission> rolePermissions = baseMapper.selectList(wrapper);

        rolePermissions.stream().forEach(rolePermission -> {
            permissionIds.add(rolePermission.getPermissionId());
        });

        return permissionIds;

    }
}
