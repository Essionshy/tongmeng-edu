package com.tingyu.tongmeng.edu.service.edu.filter;

import com.tingyu.tongmeng.edu.commons.ResultException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 评论敏感词汇过滤
 *
 * @Author essionshy
 * @Create 2020/11/4 15:25
 * @Version tongmeng-edu
 */
@Slf4j
public final class CommentFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("课程评论过滤请求进入。。。。");
        // pass the request along the filter chain
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            this.invokeWithWrappedRequest(request, response, filterChain);

        } else {

            throw new ResultException(20003, "Supported HTTP Request");
        }


        log.info("课程评论过滤请求退出。。。。");


    }

    private void invokeWithWrappedRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        request.setAttribute("this","java");
        CommentRequest requestWrapper = new CommentRequest(request);


        //将传递的ServletRequest对象转化为自定义的Request对象，即可实现非法字符的过滤
        try {
            filterChain.doFilter(requestWrapper, response);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public void destroy() {

    }


}
