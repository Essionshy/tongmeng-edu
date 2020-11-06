package com.tingyu.tongmeng.edu.service.acl.utils;

import com.tingyu.tongmeng.edu.service.acl.entity.Permission;
import com.tingyu.tongmeng.edu.service.acl.vo.PermissionVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/3 20:20
 * @Version tongmeng-edu
 */

public class PermissionTreeBuilderUtil {

    /**
     * 构建权限树
     * @param permissionList
     * @return
     */
    public static List<PermissionVo> build(List<Permission> permissionList){

        List<PermissionVo> permissionVoList = new ArrayList<>();
        //2.查询所有一级菜单列表
        for (Permission permission : permissionList) {

            if (permission.getPid().equals(Constants.FIRST_LEVEL_PARENT_ID)) {
                PermissionVo permissionVo = new PermissionVo();
                BeanUtils.copyProperties(permission, permissionVo);
                permissionVo.setChildren(getChildren(permission, permissionList));
                permissionVoList.add(permissionVo);
            }

        }
        return permissionVoList;
    }

    private static List<PermissionVo> getChildren(Permission permission, List<Permission> permissionList) {

        List<PermissionVo> children = new ArrayList<>();

        for (Permission p : permissionList) {

            if (p.getPid().equals(permission.getId())) {

                PermissionVo permissionVo = new PermissionVo();
                BeanUtils.copyProperties(p, permissionVo);
                permissionVo.setChildren(getChildren(p, permissionList));
                children.add(permissionVo);
            }

        }
        return children;
    }

    /**
     *
     * @param permissions
     * @return
     */
    public static List<String> getPermissionValueFromPermission(List<Permission> permissions){
        List<String> permissionValueList=new ArrayList<>();

        permissions.stream().forEach(permission -> {
            permissionValueList.add(permission.getPermissionValue());
        });

        return permissionValueList;

    }

}
