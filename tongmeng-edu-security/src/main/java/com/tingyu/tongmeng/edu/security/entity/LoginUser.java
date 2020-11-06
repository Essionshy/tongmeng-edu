package com.tingyu.tongmeng.edu.security.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/11/2 20:22
 * @Version tongmeng-edu
 */
@Data
public class LoginUser {

    private String id;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    private String sign;


}
