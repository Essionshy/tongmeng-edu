package com.tingyu.tongmeng.edu.service.pay.service;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/31 20:49
 * @Version tongmeng-edu
 */
public interface PayService {
    Map<String,Object> getNative(String orderNo);

    Map<String, Object> getPayStatus(String orderNo);
}
