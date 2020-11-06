package com.tingyu.tongmeng.edu.service.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.commons.dto.CountMemberDTO;
import com.tingyu.tongmeng.edu.commons.dto.OrderMemberDTO;
import com.tingyu.tongmeng.edu.service.member.entity.Member;
import com.tingyu.tongmeng.edu.service.member.vo.LoginVo;
import com.tingyu.tongmeng.edu.service.member.vo.MemberVo;
import com.tingyu.tongmeng.edu.service.member.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-30
 */
public interface MemberService extends IService<Member> {

    /**
     * 登录成功后返回token
     * @param param
     * @return
     */
    String login(LoginVo param);

    /**
     * 会员注册
     * @param registerVo
     * @return
     */
    boolean register(RegisterVo registerVo);

    MemberVo getMemberVoById(String memberId);

    OrderMemberDTO getOrderMember(String memberId);

    CountMemberDTO getCountInfo(String date);
}
