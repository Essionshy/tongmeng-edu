package com.tingyu.tongmeng.edu.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.commons.dto.OrderMemberDTO;
import com.tingyu.tongmeng.edu.service.edu.dao.CommentMapper;
import com.tingyu.tongmeng.edu.service.edu.entity.Comment;
import com.tingyu.tongmeng.edu.service.edu.remote.MemberFeignService;
import com.tingyu.tongmeng.edu.service.edu.service.CommentService;
import com.tingyu.tongmeng.edu.service.edu.vo.front.CommentFrontVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public boolean save(CommentFrontVo vo, String memberId) {
        Comment comment = new Comment();
        //根据会员号查询会员信息
        OrderMemberDTO member = memberFeignService.getOrderMember(memberId);
        log.info("=memberFeignService======member============={}",member);
        //课程评论会员信息赋值
        if(member!=null){
            comment.setMemberId(memberId);
            comment.setAvatar(member.getAvatar());
            comment.setNickname(member.getNickname());
        }
        if(vo!=null){
            comment.setCourseId(vo.getCourseId());
            comment.setContent(vo.getContent());
            comment.setTeacherId(vo.getTeacherId());
        }
        int count = baseMapper.insert(comment);
        return count > 0;
    }

    @Override
    public Map<String, Object> list(Integer page, Integer limit, String courseId) {
        //根据课程ID查询课程获取讲师
      Map<String, Object> map = new HashMap<>();
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_create");//根据评论时间倒序排列

        Page<Comment> commentPage = new Page<Comment>(page, limit);
        baseMapper.selectPage(commentPage,wrapper);

        long total = commentPage.getTotal(); //总评论数据
        List<Comment> commentList = commentPage.getRecords();//评论数据集合
        long current = commentPage.getCurrent();
        long pages = commentPage.getPages();
        boolean hasPrevious = commentPage.hasPrevious();
        boolean hasNext = commentPage.hasNext();

        map.put("total",total);
        map.put("commentList",commentList);
        map.put("current",current);
        map.put("pages",pages);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);

        return map;
    }
}
