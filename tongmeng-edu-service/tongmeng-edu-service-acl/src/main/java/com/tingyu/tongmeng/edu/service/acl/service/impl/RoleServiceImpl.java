package com.tingyu.tongmeng.edu.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.commons.ResultException;
import com.tingyu.tongmeng.edu.service.acl.dao.RoleMapper;
import com.tingyu.tongmeng.edu.service.acl.entity.Role;
import com.tingyu.tongmeng.edu.service.acl.service.RoleService;
import com.tingyu.tongmeng.edu.service.acl.service.UserRoleService;
import com.tingyu.tongmeng.edu.service.acl.utils.RoleUtil;
import com.tingyu.tongmeng.edu.service.acl.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Autowired
    UserRoleService userRoleService;

    @Override
    public List<RoleVo> listByUserId(String userId) {

        List<String> roleIds = userRoleService.listRoleIdsByUserId(userId);

        List<RoleVo> roleVoList = new ArrayList<>();
        if(!roleIds.isEmpty()){
            List<Role> roleList = baseMapper.selectBatchIds(roleIds);
            //转换为Vo
            roleVoList = RoleUtil.convertFromRole(roleList);
        }
        return roleVoList;
    }

    @Override
    public boolean create(RoleVo roleVo) {

        Role existsRole = getByRoleName(roleVo.getRoleName());
        if (existsRole != null) {
                throw new ResultException(4000, "角色已存在请不要重添加");
        }
        //如果角色不存在
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        int count = baseMapper.insert(role);
        return count > 0;
    }

    private Role getByRoleName(String roleName) {

        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", roleName);
        Role role = baseMapper.selectOne(wrapper);
        return role;

    }

    @Override
    public Map<String, Object> listByPage(Integer page, Integer limit, RoleVo roleVo) {

        Map<String, Object> map = new HashMap<>();
        Page<Role> rolePage = new Page<Role>(page, limit);

        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        //TODO 根据前端输入的关键字判断是以哪个字段作为查询条件
        String roleName = roleVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like("role_name", roleName);
        }

        this.page(rolePage, wrapper);


        long total = rolePage.getTotal();
        List<Role> roleList = rolePage.getRecords();
        map.put("total", total);
        map.put("roleList", roleList);
        return map;
    }

    @Override
    public boolean update(RoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);

        int count = baseMapper.updateById(role);
        return count > 0;
    }

    @Override
    public boolean deleteByRoleId(String roleId) {

        int count = baseMapper.deleteById(roleId);
        return count > 0;
    }

    @Override
    public int deleteBatchByIds(List<String> ids) {
        System.out.println("==================================="+ids.toString());
        log.info("vkjs:{}",ids.toString());
        int count = baseMapper.deleteBatchIds(ids);
        return count;
    }




}
