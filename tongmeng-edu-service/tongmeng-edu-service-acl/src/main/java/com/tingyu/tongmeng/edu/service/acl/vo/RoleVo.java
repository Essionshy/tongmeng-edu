package com.tingyu.tongmeng.edu.service.acl.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/3 11:13
 * @Version tongmeng-edu
 */
@Data
public class RoleVo {
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

}
