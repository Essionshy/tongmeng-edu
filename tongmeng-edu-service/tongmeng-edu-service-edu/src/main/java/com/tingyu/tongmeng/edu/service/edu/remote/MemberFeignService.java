package com.tingyu.tongmeng.edu.service.edu.remote;

import com.tingyu.tongmeng.edu.commons.dto.OrderMemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author essionshy
 * @Create 2020/11/2 11:51
 * @Version tongmeng-edu
 */
@FeignClient("tongmeng-service-member")
//@Component
public interface MemberFeignService {

    @GetMapping("/member/remote/get/{memberId}")
    OrderMemberDTO getOrderMember(@PathVariable("memberId")String memberId);
}
