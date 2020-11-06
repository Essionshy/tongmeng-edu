package com.tingyu.tongmeng.edu.service.acl.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/11/3 17:20
 * @Version tongmeng-edu
 */
public interface IndexService {
    Map<String, Object> getLoginUserInfo(String username);

    List<JSONObject> getMenu(String username);
}
