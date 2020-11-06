package com.tingyu.tongmeng.edu.service.sas.service;

import com.tingyu.tongmeng.edu.service.sas.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.sas.vo.QueryVo;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-01
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {


    boolean saveCountInfo(String day);

    Map<String, Object> getDailyInfoCount(QueryVo queryVo);
}
