package com.tingyu.tongmeng.edu.service.sas.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/11/1 23:00
 * @Version tongmeng-edu
 */
@Data
public class QueryVo {
    @ApiModelProperty("统计分析项")
    private String type;

    @ApiModelProperty("起始日期")
    private String startDate;
    @ApiModelProperty("结束日期")
    private String endDate;

}
