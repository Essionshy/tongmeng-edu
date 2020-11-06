package com.tingyu.tongmeng.edu.service.job.task;

import com.tingyu.tongmeng.edu.commons.DateUtils;
import com.tingyu.tongmeng.edu.service.job.remote.StatisticalAnalysisFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/1 21:41
 * @Version tongmeng-edu
 */
@Component
@Slf4j
public class StatisticalAnalysisTask {


    @Autowired
    StatisticalAnalysisFeignService statisticalAnalysisFeignService;


    @Scheduled(cron = "0 0 1 1/1 * ?")
    public void run(){
        log.info("执行 statisticalAnalysisFeignService task");
        statisticalAnalysisFeignService.save(DateUtils.formatLastDay());

    }

}
