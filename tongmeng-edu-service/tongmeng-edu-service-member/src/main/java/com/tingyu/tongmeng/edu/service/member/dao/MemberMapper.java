package com.tingyu.tongmeng.edu.service.member.dao;

import com.tingyu.tongmeng.edu.service.member.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-30
 */
public interface MemberMapper extends BaseMapper<Member> {

    int countRegisterDaily(@Param("day") String day);

    int countLoginDaily(@Param("day") String day);


}
