package com.tingyu.tongmeng.edu.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.edu.entity.Comment;
import com.tingyu.tongmeng.edu.service.edu.vo.front.CommentFrontVo;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
public interface CommentService extends IService<Comment> {

    boolean save(CommentFrontVo vo, String memberId);


    Map<String,Object> list(Integer page, Integer limit, String courseId);
}
