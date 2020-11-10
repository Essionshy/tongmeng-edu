package com.tingyu.tongmeng.edu.service.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.acl.entity.User;
import com.tingyu.tongmeng.edu.service.acl.vo.UserVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
public interface UserService extends IService<User> {


    UserVo getByUsername(String username);


    Map<String,Object>  listByPage(Integer page, Integer limit, UserVo userVo);

    boolean create(UserVo userVo);

    boolean update(UserVo userVo);

    boolean deleteByUserId(String userId);

    int deleteBatchByIds(String[] ids);

    UserVo getUserById(String id);


    Map<String,Object> listRoleByUserId(String userId);

    boolean doAssign(String userId, String[] roleIds);
}
