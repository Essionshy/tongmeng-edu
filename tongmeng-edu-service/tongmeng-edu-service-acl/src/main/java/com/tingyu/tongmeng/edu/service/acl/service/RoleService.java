package com.tingyu.tongmeng.edu.service.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.acl.entity.Role;
import com.tingyu.tongmeng.edu.service.acl.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
public interface RoleService extends IService<Role> {

    List<RoleVo> listByUserId(String id);

    boolean create(RoleVo roleVo);

   Map<String,Object> listByPage(Integer page, Integer limit, RoleVo roleVo);

    boolean update(RoleVo roleVo);

    boolean deleteByRoleId(String roleId);

   int deleteBatchByIds(List<String> ids);

}
