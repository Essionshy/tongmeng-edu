package com.tingyu.tongmeng.edu.service.acl.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.acl.service.PermissionService;
import com.tingyu.tongmeng.edu.service.acl.vo.PermissionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/admin/sys/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("获取权限菜单列表的树形结构")
    @GetMapping("list/tree")
    public R listPermissionTree() {
        List<PermissionVo> permissionList = permissionService.listPermissionTree();
        return R.ok().data("permissionList", permissionList);

    }

    @ApiOperation("根据id级联删除菜单")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id) {
        boolean isSuccess = permissionService.delete(id);
        if (isSuccess) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    @ApiOperation("更新用户")
    @PutMapping("update")
    public R update(@RequestBody PermissionVo permissionVo){
        boolean isSuccess = permissionService.update(permissionVo);

        if(isSuccess){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("allot")
    public R allot( String roleId, String[] permissionIds) {

        boolean isSuccess = permissionService.allotPermissionToRole(roleId, permissionIds);
        if (isSuccess) {

            return R.ok();
        } else {
            return R.error();
        }
    }
}

