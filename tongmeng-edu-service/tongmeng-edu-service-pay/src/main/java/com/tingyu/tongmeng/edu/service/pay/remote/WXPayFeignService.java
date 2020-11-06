package com.tingyu.tongmeng.edu.service.pay.remote;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/11/2 11:29
 * @Version tongmeng-edu
 */
//@FeignClient("")
public interface WXPayFeignService {
    Map createPayNative();
}
