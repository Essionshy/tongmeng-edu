package com.tingyu.tongmeng.edu.service.acl.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.acl.service.RoleService;
import com.tingyu.tongmeng.edu.service.acl.vo.RoleVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/admin/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("条件分页查询用户")
    @PostMapping("list/{page}/{limit}")
    public R list(
            @PathVariable("page")Integer page,
            @PathVariable("limit")Integer limit,
            @RequestBody RoleVo roleVo){
        Map<String,Object> map = roleService.listByPage(page, limit, roleVo);




        return R.ok().data(map);
    }

    @PostMapping("save")
    public R create(@RequestBody RoleVo roleVo){

        boolean isSuccess = roleService.create(roleVo);
        if(isSuccess){
            return R.ok();
        }else {
            return R.error();
        }

    }
    @ApiOperation("更新用户")
    @PutMapping("update")
    public R update(@RequestBody RoleVo roleVo){
        boolean isSuccess = roleService.update(roleVo);

        if(isSuccess){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("根据用户ID删除用户")
    @DeleteMapping("delete/{roleId}")
    public R delete(@PathVariable String roleId){
        boolean isSuccess = roleService.deleteByRoleId(roleId);
        if(isSuccess){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("delete/batch")
    public R deleteByBatch(String [] ids){

        Map<String,Object> map=roleService.deleteBatchByIds(ids);

        return R.ok().data(map);
    }

}

