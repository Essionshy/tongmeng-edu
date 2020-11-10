package com.tingyu.tongmeng.edu.service.acl.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.acl.entity.Permission;
import com.tingyu.tongmeng.edu.service.acl.vo.PermissionVo;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
public interface PermissionService extends IService<Permission> {

    List<PermissionVo> listPermissionTree();

    boolean delete(String id);


    boolean doAssign(String roleId, List<String> permissionIds);

    List<String> listPermissionIdsByUserId(String id);

    boolean update(PermissionVo permissionVo);

    List<PermissionVo> listPermissionListByUserId(String id);

    /**
     * 根据用户id查询权限值列表
     * @param id  用户id
     * @return
     */
    List<String> listPermissionValueByUserId(String id);

    /**
     * 查询所有权限值列表
     * @return
     */
    List<String> listAllPermissionValue();

    public List<JSONObject> selectPermissionByUserId(String userId);

    List<PermissionVo> listPermissionListByRoleId(String roleId);

    List<PermissionVo> listAllPermissionList();

    boolean save(PermissionVo permissionVo);
}
