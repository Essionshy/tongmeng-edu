package com.tingyu.tongmeng.edu.service.job.remote;

import com.tingyu.tongmeng.edu.commons.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author essionshy
 * @Create 2020/11/1 21:43
 * @Version tongmeng-edu
 */
@Component
@FeignClient("tongmeng-service-sas")
public interface StatisticalAnalysisFeignService {

    @PostMapping("/sas/count/save/{day}")
     R save(@PathVariable("day")String day);
}
