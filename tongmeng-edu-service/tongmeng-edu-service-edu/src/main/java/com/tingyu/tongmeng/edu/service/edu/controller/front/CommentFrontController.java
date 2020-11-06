package com.tingyu.tongmeng.edu.service.edu.controller.front;

import com.tingyu.tongmeng.edu.commons.JwtUtils;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.service.CommentService;
import com.tingyu.tongmeng.edu.service.edu.vo.front.CommentFrontVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/11/2 11:40
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("edu/comment/front")

public class CommentFrontController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("添加课程评论")
    @PostMapping("save")
    public R save(@RequestBody CommentFrontVo vo, HttpServletRequest request) {



        boolean isSuccess = commentService.save(vo, JwtUtils.getUserIdByToken(request));
        if (isSuccess) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    @ApiOperation("分页查询某老师下某课程的评论")
    @GetMapping("list/{page}/{limit}/{courseId}")
    public R list(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit,
            @PathVariable("courseId") String courseId) {
        Map<String, Object> map = commentService.list(page, limit, courseId);
        return R.ok().data(map);
    }

}
