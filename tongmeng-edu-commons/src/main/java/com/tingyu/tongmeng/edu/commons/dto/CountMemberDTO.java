package com.tingyu.tongmeng.edu.commons.dto;

import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/11/1 17:45
 * @Version tongmeng-edu
 */
@Data
public class CountMemberDTO {

    /*统计每日注册人数*/
    private Integer countRegisterDaily;
    /*统计每日登录人数*/
    private Integer countLoginDaily;



}
