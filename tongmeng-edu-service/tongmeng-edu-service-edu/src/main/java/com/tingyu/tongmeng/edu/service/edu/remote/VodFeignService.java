package com.tingyu.tongmeng.edu.service.edu.remote;

import com.tingyu.tongmeng.edu.commons.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * FeignClient 调用远程服务
 * @Author essionshy
 * @Create 2020/10/29 10:13
 * @Version tongmeng-edu
 */

@FeignClient("aliyun-vod")
@Component //注入Spring IOC 进行管理 一定记得加，不然服务调用不成功
public interface VodFeignService {

    @DeleteMapping("/vod/video/delete/{videoId}")
    public R delete(@PathVariable("videoId") String videoId);
}
