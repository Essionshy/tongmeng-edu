package com.tingyu.tongmeng.edu.service.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.commons.JwtUtils;
import com.tingyu.tongmeng.edu.commons.MD5Utils;
import com.tingyu.tongmeng.edu.commons.ResultException;
import com.tingyu.tongmeng.edu.commons.dto.CountMemberDTO;
import com.tingyu.tongmeng.edu.commons.dto.OrderMemberDTO;
import com.tingyu.tongmeng.edu.service.member.dao.MemberMapper;
import com.tingyu.tongmeng.edu.service.member.entity.Member;
import com.tingyu.tongmeng.edu.service.member.service.MemberService;
import com.tingyu.tongmeng.edu.service.member.utils.Constants;
import com.tingyu.tongmeng.edu.service.member.vo.LoginVo;
import com.tingyu.tongmeng.edu.service.member.vo.MemberVo;
import com.tingyu.tongmeng.edu.service.member.vo.RegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-30
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(LoginVo param) {

        //验证用户是否存在
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", param.getMobile());
        Member member = memberMapper.selectOne(wrapper);
        if (member == null) {
            throw new ResultException(200, "用户不存在");
        }

        //验证用户是否被禁用

        if (member.getDisabled()) {
            throw new ResultException(200, "用户已被禁用，请联系管理员！");
        }

        //验证密码是否正确

        if (!member.getPassword().equals(MD5Utils.encrypt(param.getPassword()))) {
            throw new ResultException(2000, "密码不正确");
        }

        //验证通过根据会员ID和会员昵称返回token
        String token = JwtUtils.getToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public boolean register(RegisterVo registerVo) {

        String mobile = registerVo.getMobile();
        String verifyCode = registerVo.getCode();

        //校验用户手机号是否已经注册
        validateRegistered(registerVo);
        //校验验证码是否正确
        String code = stringRedisTemplate.opsForValue().get(mobile);
        log.info("=======redis=======code:{}",code);
        log.info("=======vo=======verifyCode:{}",verifyCode);

        if (!verifyCode.equals(code)) {
            //TODO redis中取出的数据带有引号与传入的值不匹配，修改序列化器为 StringRedisSeril
            throw new ResultException(200, "验证码不匹配！！");
        }
        //保存会员注册信息
        Member member = new Member();
        member.setNickname(registerVo.getNickname());
        member.setMobile(mobile);
        member.setDisabled(false);
        member.setPassword(MD5Utils.encrypt(registerVo.getPassword()));
        member.setAvatar(Constants.DEFAULT_AVATAR_URL); //设置默认头像

        int count = memberMapper.insert(member);
        return count > 0;
    }

    @Override
    public MemberVo getMemberVoById(String memberId) {

        MemberVo memberVo = new MemberVo();
        Member member = getById(memberId);
        if (member != null) {
            BeanUtils.copyProperties(member, memberVo);
        }
        return memberVo;
    }

    @Override
    public OrderMemberDTO getOrderMember(String memberId) {
        OrderMemberDTO orderMemberDTO = new OrderMemberDTO();
        Member member = baseMapper.selectById(memberId);
        if(member!=null){
            BeanUtils.copyProperties(member,orderMemberDTO);
        }
        return orderMemberDTO;
    }

    @Override
    public CountMemberDTO getCountInfo(String date) {

        CountMemberDTO countInfo = new CountMemberDTO();
        int countRegisterDaily = memberMapper.countRegisterDaily(date);
        int countLoginDaily = memberMapper.countLoginDaily(date);

        countInfo.setCountRegisterDaily(countRegisterDaily);
        countInfo.setCountLoginDaily(countLoginDaily);

        return countInfo;
    }

    private void validateRegistered(RegisterVo registerVo) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", registerVo.getMobile());
        Member member = this.getOne(wrapper);
        if (member != null) {
            throw new ResultException(500, "用户手机号已经注册");
        }
    }
}
