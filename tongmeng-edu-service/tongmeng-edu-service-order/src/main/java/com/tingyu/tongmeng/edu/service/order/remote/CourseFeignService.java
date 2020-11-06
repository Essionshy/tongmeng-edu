package com.tingyu.tongmeng.edu.service.order.remote;

import com.tingyu.tongmeng.edu.commons.dto.OrderCourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author essionshy
 * @Create 2020/10/31 15:58
 * @Version tongmeng-edu
 */
@FeignClient("tongmeng-service-edu")
@Component
public interface CourseFeignService {


    @GetMapping("/edu/course/remote/get/{courseId}")
     OrderCourseDTO getOrderCourseDTO(@PathVariable("courseId")String courseId);
}
