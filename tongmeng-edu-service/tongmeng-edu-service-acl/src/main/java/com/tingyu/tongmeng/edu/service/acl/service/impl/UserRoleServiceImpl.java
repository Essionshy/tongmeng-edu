package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.acl.dao.UserRoleMapper;
import com.tingyu.tongmeng.edu.service.acl.entity.UserRole;
import com.tingyu.tongmeng.edu.service.acl.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public boolean saveByUserId(String userId, String[] roleIds) {

        List<String> existsRoleIds = listRoleIdsByUserId(userId);

        List<String> saveRoleIds=new ArrayList<>();

        for (String roleId:roleIds){
            if(!existsRoleIds.contains(roleId)){
                saveRoleIds.add(roleId);
            }

        }

        //批量构造要保存的角色对象
        List<UserRole> userRoleList=new ArrayList<>();
        for(String roleId :saveRoleIds){
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        return this.saveBatch(userRoleList);
    }

    @Override
    public boolean removeByUserId(String userId, String[] roleIds) {

        //删除表示前端传入的角色id小于已经存在的角色
        List<String> existsRoleIds = listRoleIdsByUserId(userId);

        List<String> frontRoleIds = Arrays.asList(roleIds);

        for(String roleId:existsRoleIds){

            if(!frontRoleIds.contains(roleId)){
                //移除多余的角色
                this.removeByUserIdAndRoleId(userId,roleId);

            }

        }
        return true;
    }

    @Override
    public void removeByUserIdAndRoleId(String userId, String roleId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("role_id",roleId);

        int count = baseMapper.delete(wrapper);



    }
}
