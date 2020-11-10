package com.tingyu.tongmeng.edu.service.acl.utils;

import com.tingyu.tongmeng.edu.service.acl.entity.Permission;
import com.tingyu.tongmeng.edu.service.acl.vo.PermissionVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2020/11/3 20:20
 * @Version tongmeng-edu
 */

public class PermissionUtil {

    /**
     * 构建权限树
     * @param permissionVoList
     * @return
     */
    public static List<PermissionVo> build(List<PermissionVo> permissionVoList){

        List<PermissionVo> permissionVoListTree = new ArrayList<>();



        //2.查询所有一级菜单列表
        for (PermissionVo permissionVo : permissionVoList) {

            if (permissionVo.getPid().equals(Constants.FIRST_LEVEL_PARENT_ID)) {

                permissionVo.setLevel(1);

                permissionVo.setChildren(getChildren(permissionVo, permissionVoList));
                //一级菜单
                permissionVoListTree.add(permissionVo);
            }

        }
        return permissionVoListTree;
    }

    private static List<PermissionVo> getChildren(PermissionVo permissionVo, List<PermissionVo> permissionList) {

        List<PermissionVo> children = new ArrayList<>();

        for (PermissionVo permission : permissionList) {
            if (permission.getPid().equals(permissionVo.getId())) {
                //注意此处为传的permissionVo的菜单层级递增
                Integer level=permissionVo.getLevel()+1;
                permission.setLevel(level);
                permission.setChildren(getChildren(permission, permissionList));
                children.add(permission);
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


    public static List<PermissionVo> convertFromPermission(List<Permission> permissions){

        return permissions.stream().map(permission -> {
           return convertFromPermission(permission);
        }).collect(Collectors.toList());
    }


    public static PermissionVo convertFromPermission(Permission permission){
        PermissionVo permissionVo = new PermissionVo();
        if(permission!=null){
            BeanUtils.copyProperties(permission,permissionVo);
        }
        return permissionVo;
    }

}
