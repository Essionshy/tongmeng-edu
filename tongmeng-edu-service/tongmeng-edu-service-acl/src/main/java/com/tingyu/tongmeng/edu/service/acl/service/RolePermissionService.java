package com.tingyu.tongmeng.edu.service.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.acl.entity.RolePermission;

import java.util.List;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
public interface RolePermissionService extends IService<RolePermission> {

    List<RolePermission> getByRoleId(String id);

     List<String> getPermissionIdsByRoleId(String roleId);
}
