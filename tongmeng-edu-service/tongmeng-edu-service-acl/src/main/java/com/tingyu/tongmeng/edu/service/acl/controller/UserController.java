package com.tingyu.tongmeng.edu.service.acl.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.acl.service.UserService;
import com.tingyu.tongmeng.edu.service.acl.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/admin/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("条件分页查询用户")
    @PostMapping("list/{page}/{limit}")
    public R list(
            @PathVariable("page")Integer page,
            @PathVariable("limit")Integer limit,
            @RequestBody UserVo userVo){
        Map<String,Object>  map = userService.listByPage(page, limit, userVo);
        return R.ok().data(map);
    }

    @PostMapping("save")
    public R create(@RequestBody UserVo userVo){

        boolean isSuccess = userService.create(userVo);
        if(isSuccess){
            return R.ok();
        }else {
            return R.error();
        }

    }
    @ApiOperation("更新用户")
    @PutMapping("update")
    public R update(@RequestBody UserVo userVo){
        boolean isSuccess = userService.update(userVo);

        if(isSuccess){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("根据用户ID删除用户")
    @DeleteMapping("delete/{userId}")
    public R delete(@PathVariable String userId){
        boolean isSuccess = userService.deleteByUserId(userId);
        if(isSuccess){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("delete/batch")
    public R deleteByBatch(@RequestBody String [] ids){

        int count=userService.deleteBatchByIds(ids);

        return R.ok().data("count",count);
    }


    @GetMapping("get/{id}")
    public R get(@PathVariable("id") String id){

        UserVo user = userService.getUserById(id);
        return R.ok().data("user",user);
    }


    @GetMapping("toAssign/{userId}")
    public R toAssign(@PathVariable("userId")String userId){
        Map<String ,Object> map = userService.listRoleByUserId(userId);
        return R.ok().data(map);
    }

    @PostMapping("doAssign/{userId}")
    public R doAssign(@PathVariable("userId")String userId,@RequestBody String[] roleIds){
        userService.doAssign(userId,roleIds);
        return R.ok();
    }

}

