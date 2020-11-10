package com.tingyu.tongmeng.edu.service.acl.service;

import com.tingyu.tongmeng.edu.service.acl.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
public interface UserRoleService extends IService<UserRole> {

    List<String> listRoleIdsByUserId(String userId);

    boolean saveByUserId(String userId, String[] roleIds);

    boolean removeByUserId(String userId, String[] roleIds);

    void removeByUserIdAndRoleId(String userId, String roleId);
}
