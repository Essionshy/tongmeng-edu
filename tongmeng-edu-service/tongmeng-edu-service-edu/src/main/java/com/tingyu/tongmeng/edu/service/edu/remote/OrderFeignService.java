package com.tingyu.tongmeng.edu.service.edu.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author essionshy
 * @Create 2020/11/1 16:22
 * @Version tongmeng-edu
 */
@FeignClient("tongmeng-service-order")
public interface OrderFeignService {
    @GetMapping("/order/course/status/{courseId}/{memberId}")
    public boolean isBuy(@PathVariable("courseId")String courseId, @PathVariable("memberId")String memberId);
}
