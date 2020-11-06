package com.tingyu.tongmeng.edu.service.sas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.commons.dto.CountMemberDTO;
import com.tingyu.tongmeng.edu.service.sas.dao.StatisticsDailyMapper;
import com.tingyu.tongmeng.edu.service.sas.entity.StatisticsDaily;
import com.tingyu.tongmeng.edu.service.sas.remote.MemberFeignService;
import com.tingyu.tongmeng.edu.service.sas.service.StatisticsDailyService;
import com.tingyu.tongmeng.edu.service.sas.utils.QueryType;
import com.tingyu.tongmeng.edu.service.sas.vo.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-11-01
 */
@Service
@Slf4j
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private MemberFeignService memberFeignService;


    /**
     * 保存每日访问数据
     *
     * @param day
     * @return
     */
    @Override
    public boolean saveCountInfo(String day) {

        //1.删除之前当天保存的数据，以此保证存储最新的数据

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);
        //2.添加查询出的最新数据
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        CountMemberDTO countInfo = memberFeignService.getCountInfo(day);
        statisticsDaily.setRegisterNum(countInfo.getCountRegisterDaily());
        statisticsDaily.setLoginNum(countInfo.getCountLoginDaily());
        statisticsDaily.setDateCalculated(day);
        int count = baseMapper.insert(statisticsDaily);
        return count > 0;
    }

    @Override
    public Map<String, Object> getDailyInfoCount(QueryVo queryVo) {
        log.info("=============getDailyInfoCount=================:{}", queryVo);
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(queryVo.getStartDate()) && !StringUtils.isEmpty(queryVo.getEndDate()))
            wrapper.between("date_calculated", queryVo.getStartDate(), queryVo.getEndDate());
        //mp 提供的查询指定列的数据 queryVo.getType()由前端下拉列表中的值指定为字段列名对应
        wrapper.select("date_calculated", queryVo.getType());
        List<StatisticsDaily> dailyList = baseMapper.selectList(wrapper);

        log.info("=============dailyList=================:{}", dailyList.size());

        //封装返回前端显示数据
        List<String> dateList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        //遍历结果集，对返回项进行赋值
        dailyList.forEach(statisticsDaily -> {
            dateList.add(statisticsDaily.getDateCalculated());

            switch (queryVo.getType()) {
                case QueryType.LOGIN_NUM:
                    countList.add(statisticsDaily.getLoginNum());
                    break;
                case QueryType.COURSE_NUM:
                    countList.add(statisticsDaily.getCourseNum());
                    break;
                case QueryType.REGISTER_NUM:
                    countList.add(statisticsDaily.getRegisterNum());
                    break;
                case QueryType.VIDEO_VIEW_NUM:
                    countList.add(statisticsDaily.getVideoViewNum());
                    break;
            }
        });
        map.put("dateList", dateList);
        map.put("countList", countList);
        return map;
    }
}
