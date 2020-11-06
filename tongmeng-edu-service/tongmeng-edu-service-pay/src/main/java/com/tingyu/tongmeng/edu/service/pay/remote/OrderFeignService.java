package com.tingyu.tongmeng.edu.service.pay.remote;

import com.tingyu.tongmeng.edu.commons.dto.OrderDTO;
import com.tingyu.tongmeng.edu.commons.dto.PayLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author essionshy
 * @Create 2020/10/31 20:53
 * @Version tongmeng-edu
 */
@FeignClient("tongmeng-service-order")
public interface OrderFeignService {

    @GetMapping("/order/get/remote/{orderNo}")
    OrderDTO getOrderDTO(@PathVariable("orderNo")String orderNo);

    @PutMapping("/order/update/remote/status/{orderNo}")
     void updateOrderStatus(@PathVariable String orderNo);


    @PostMapping("/order/pay/log/save")
    void savePayLog(@RequestBody PayLogDTO dto);

}
