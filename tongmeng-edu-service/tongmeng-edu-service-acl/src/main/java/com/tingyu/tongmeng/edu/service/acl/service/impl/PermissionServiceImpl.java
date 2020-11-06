package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.acl.dao.PermissionMapper;
import com.tingyu.tongmeng.edu.service.acl.entity.Permission;
import com.tingyu.tongmeng.edu.service.acl.entity.RolePermission;
import com.tingyu.tongmeng.edu.service.acl.entity.User;
import com.tingyu.tongmeng.edu.service.acl.entity.UserRole;
import com.tingyu.tongmeng.edu.service.acl.service.*;
import com.tingyu.tongmeng.edu.service.acl.utils.MenuBuilderUtil;
import com.tingyu.tongmeng.edu.service.acl.utils.PermissionTreeBuilderUtil;
import com.tingyu.tongmeng.edu.service.acl.vo.PermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    private UserService userService;

    @Override
    public List<PermissionVo> listPermissionTree() {

        //1.查询所有权限菜单列表
        List<Permission> permissionList = baseMapper.selectList(null);

        //2.将查询的结果集以树形结构方式返回
        List<PermissionVo> permissionVoList = PermissionTreeBuilderUtil.build(permissionList);

        return permissionVoList;
    }

    @Override
    public boolean delete(String id) {
        //级联删除菜单列表
        List<String> permissionIds = new ArrayList<>();
        this.queryAllChildrenPermission(id, permissionIds);
        permissionIds.add(id);
        int count = baseMapper.deleteBatchIds(permissionIds);
        return count > 0;
    }

    @Override
    public boolean allotPermissionToRole(String roleId, String[] permissionIds) {
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermissionList.add(rolePermission);

        }

        boolean isSuccess = rolePermissionService.saveBatch(rolePermissionList);

        return isSuccess;
    }

    @Override
    public List<String> listPermissionIdsByUserId(String userId) {


        List<String> permissionListValue = new ArrayList<>();
        //根据用户ID查询用户角色

        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.select("role_id");

        List<UserRole> userRoles = userRoleService.list(wrapper);
        userRoles.stream().forEach(userRole -> {
            //根据角色ID查询权限
            QueryWrapper<RolePermission> roleWrapper = new QueryWrapper<>();
            roleWrapper.eq("role_id", userRole.getRoleId());
            roleWrapper.select("permission_id");
            List<RolePermission> rolePermissionList = rolePermissionService.list(roleWrapper);
            rolePermissionList.stream().forEach(rolePermission -> {
                permissionListValue.add(rolePermission.getPermissionId());
            });
        });
        return permissionListValue;
    }

//    @Override
//    public List<PermissionVo> listByUserId(String userId) {
//
//        //由于不同角色可以拥有相同的权限，为了去重，使用Set<String>
//        List<RoleVo> roleVoList = roleService.listByUserId(userId);
//
//        Set<String> permissionIds=new HashSet<>();
//        roleVoList.stream().forEach(roleVo -> {
//            //根据角色id
//
//            List<String> permissionIdList = rolePermissionService.getPermissionIdsByRoleId(roleVo.getId());
//            //遍历添加到集合中并去重
//            for(String permissionId:permissionIdList){
//                permissionIds.add(permissionId);
//            }
//        });
//        List<Permission> permissionList = baseMapper.selectBatchIds(permissionIds);
//
//        //Permission 转换 PermissionVo
//        List<PermissionVo> permissionVoList = permissionList.stream().map(permission -> {
//            PermissionVo permissionVo = new PermissionVo();
//
//            return permissionVo;
//        }).collect(Collectors.toList());
//        return permissionVoList;
//    }

    @Override
    public boolean update(PermissionVo permissionVo) {

        Permission permission = new Permission();

        BeanUtils.copyProperties(permissionVo, permission);

        int count = baseMapper.updateById(permission);


        return count > 0;
    }

    @Override
    public List<PermissionVo> listPermissionListByUserId(String id) {


        List<Permission> permissions = baseMapper.selectBatchIds(this.listPermissionIdsByUserId(id));

        List<PermissionVo> permissionVoList = PermissionTreeBuilderUtil.build(permissions);

        return permissionVoList;
    }

    @Override
    public List<String> listPermissionValueByUserId(String id) {
        List<String> permissionValueList;
        if (isSysAdmin(id)) {
            permissionValueList = this.listAllPermissionValue();
        } else {
            List<Permission> permissions = baseMapper.selectBatchIds(this.listPermissionIdsByUserId(id));

            permissionValueList = PermissionTreeBuilderUtil.getPermissionValueFromPermission(permissions);
        }
        return permissionValueList;
    }

    //查询所有权限值，条件类型为2
    @Override
    public List<String> listAllPermissionValue() {

        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 2);
        wrapper.select("permission_value");
        List<Permission> permissionList = baseMapper.selectList(wrapper);
        return PermissionTreeBuilderUtil.getPermissionValueFromPermission(permissionList);
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<PermissionVo> selectPermissionList;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = listPermissionTree();
        } else {
            selectPermissionList = listPermissionListByUserId(userId);
        }
        log.info("permissionByUserId:{}",selectPermissionList);

        List<JSONObject> result = MenuBuilderUtil.bulid(selectPermissionList);
        return result;
    }


    private void queryAllChildrenPermission(String id, List<String> permissionIds) {
        //逐级查询菜单id
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        wrapper.select("id");
        List<Permission> permissions = baseMapper.selectList(wrapper);
        permissions.forEach(item -> {
            permissionIds.add(item.getId());
            //递归调用查询所有
            this.queryAllChildrenPermission(item.getId(), permissionIds);

        });
    }

    /**
     * 判断用户是否系统管理员
     *
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);

        if (null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }

}
