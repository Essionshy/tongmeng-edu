package com.tingyu.tongmeng.edu.service.job.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author essionshy
 * @Create 2020/11/4 16:53
 * @Version tongmeng-edu
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器名称"+filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println(" call doFilter ()");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println(" call filter destroy method");
    }
}
