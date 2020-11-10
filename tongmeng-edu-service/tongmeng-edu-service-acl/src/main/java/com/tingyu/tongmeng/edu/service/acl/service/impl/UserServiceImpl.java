package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.commons.MD5Utils;
import com.tingyu.tongmeng.edu.service.acl.dao.UserMapper;
import com.tingyu.tongmeng.edu.service.acl.entity.Role;
import com.tingyu.tongmeng.edu.service.acl.entity.User;
import com.tingyu.tongmeng.edu.service.acl.service.PermissionService;
import com.tingyu.tongmeng.edu.service.acl.service.RoleService;
import com.tingyu.tongmeng.edu.service.acl.service.UserRoleService;
import com.tingyu.tongmeng.edu.service.acl.service.UserService;
import com.tingyu.tongmeng.edu.service.acl.vo.RoleVo;
import com.tingyu.tongmeng.edu.service.acl.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleService roleService;


    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserVo getByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = baseMapper.selectOne(wrapper);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }



    @Override
    public Map<String,Object>  listByPage(Integer page, Integer limit, UserVo userVo) {
        Map<String,Object> map=new HashMap<>();
        Page<User> rolePage = new Page<User>(page, limit);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //TODO 根据前端输入的关键字判断是以哪个字段作为查询条件
        String username = userVo.getUsername();
        if(!StringUtils.isEmpty(username)){
            wrapper.like("username",username);
        }

        baseMapper.selectPage(rolePage,wrapper);

        long total = rolePage.getTotal();
        List<User> userList = rolePage.getRecords();
        map.put("total",total);
        map.put("userList",userList);



        return map;
    }

    @Override
    public boolean create(UserVo userVo) {

        User user = new User();
        BeanUtils.copyProperties(userVo,user);
        user.setPassword(MD5Utils.encrypt(userVo.getPassword()));

        int count = baseMapper.insert(user);
        return count>0;
    }

    @Override
    public boolean update(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo,user);
        int count = baseMapper.updateById(user);
        return count>0;
    }

    @Override
    public boolean deleteByUserId(String userId) {

        int count = baseMapper.deleteById(userId);
        return count>0;
    }

    @Override
    public int deleteBatchByIds( String[] ids) {

        List<String> idList=new ArrayList<>();

        for(String id:ids){
            idList.add(id);
        }
        int count = baseMapper.deleteBatchIds(idList);


        return count;
    }

    @Override
    public UserVo getUserById(String id) {
        UserVo userVo = new UserVo();
        User user = baseMapper.selectById(id);
        if(user!=null){
            BeanUtils.copyProperties(user,userVo);
        }
        return userVo;
    }



    @Override
    public Map<String,Object> listRoleByUserId(String userId) {

        Map<String,Object> map=new HashMap<>();
        //获取所有角色列表
        List<Role> allRoleList = roleService.list();

        //获取
        List<RoleVo> existsRoleList = roleService.listByUserId(userId);


        map.put("allRoleList",allRoleList);
        map.put("existsRoleList",existsRoleList);

        return map;
    }

    @Override
    public boolean doAssign(String userId, String[] roleIds) {

        boolean isSuccess;
        //判断是新增还是删除

        List<RoleVo> roleVos = roleService.listByUserId(userId);
        if(roleIds.length>roleVos.size()){
            //给用户添加角色
            isSuccess = userRoleService.saveByUserId(userId, roleIds);


        }else {
            //删除用户角色
            isSuccess = userRoleService.removeByUserId(userId,roleIds);


        }



        return isSuccess;
    }
}
