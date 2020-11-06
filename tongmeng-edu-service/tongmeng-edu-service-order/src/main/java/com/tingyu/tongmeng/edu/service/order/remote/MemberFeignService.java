package com.tingyu.tongmeng.edu.service.order.remote;

import com.tingyu.tongmeng.edu.commons.dto.OrderMemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author essionshy
 * @Create 2020/10/31 15:58
 * @Version tongmeng-edu
 */
@FeignClient("tongmeng-service-member")
@Component
public interface MemberFeignService {

    @GetMapping("/member/remote/get/{memberId}")
    public OrderMemberDTO getOrderMember(@PathVariable("memberId")String memberId);
}
