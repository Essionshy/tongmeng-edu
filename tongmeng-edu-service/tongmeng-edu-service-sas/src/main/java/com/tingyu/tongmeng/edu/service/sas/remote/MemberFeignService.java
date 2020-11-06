package com.tingyu.tongmeng.edu.service.sas.remote;

import com.tingyu.tongmeng.edu.commons.dto.CountMemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author essionshy
 * @Create 2020/11/1 20:34
 * @Version tongmeng-edu
 */
@Component
@FeignClient("tongmeng-service-member")
public interface MemberFeignService {
    @GetMapping("/sys/member/count/{day}")
     CountMemberDTO getCountInfo(@PathVariable("day") String day);

}
