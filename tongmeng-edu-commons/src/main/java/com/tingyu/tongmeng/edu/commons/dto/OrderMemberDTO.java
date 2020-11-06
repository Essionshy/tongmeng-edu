package com.tingyu.tongmeng.edu.commons.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于订单服务远程调用会员服务传输对象
 *
 * @Author essionshy
 * @Create 2020/10/31 15:25
 * @Version tongmeng-edu
 */
@Data
public class OrderMemberDTO {

    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "手机号")
    private String mobile;



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
