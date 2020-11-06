package com.tingyu.tongmeng.edu.service.member.controller;


import com.tingyu.tongmeng.edu.commons.JwtUtils;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.commons.dto.CountMemberDTO;
import com.tingyu.tongmeng.edu.commons.dto.OrderMemberDTO;
import com.tingyu.tongmeng.edu.service.member.service.MemberService;
import com.tingyu.tongmeng.edu.service.member.utils.ValidateUtils;
import com.tingyu.tongmeng.edu.service.member.vo.LoginVo;
import com.tingyu.tongmeng.edu.service.member.vo.MemberVo;
import com.tingyu.tongmeng.edu.service.member.vo.RegisterVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/member")

public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation("会员登录请求")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        //用户登录校验
        ValidateUtils.validateLogin(loginVo);

        String token = memberService.login(loginVo);
        return R.ok().data("token",token);
    }


    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        ValidateUtils.validateRegister(registerVo);
        boolean isSuccess = memberService.register(registerVo);
        if(isSuccess){
           return R.ok();
        }else{
         return    R.error();
        }
    }

    @ApiOperation("根据token获取会员信息")
    @GetMapping("get/token")
    public R getMemberInfoByToken(HttpServletRequest request){
        String memberId = JwtUtils.getUserIdByToken(request);

        MemberVo member = memberService.getMemberVoById(memberId);


        return R.ok().data("member",member);
    }

    @GetMapping("remote/get/{memberId}")
    public OrderMemberDTO getOrderMember(@PathVariable("memberId")String memberId){

        OrderMemberDTO orderMemberDTO = memberService.getOrderMember(memberId);
        return orderMemberDTO;
    }

/*统计用户数据远程调用接口API*/

    @GetMapping("count/{day}")
    public CountMemberDTO getCountInfo(@PathVariable("day") String day){
        CountMemberDTO countInfo = memberService.getCountInfo(day);
        return countInfo;
    }
}

