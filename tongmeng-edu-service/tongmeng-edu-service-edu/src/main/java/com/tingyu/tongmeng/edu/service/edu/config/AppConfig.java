package com.tingyu.tongmeng.edu.service.edu.config;

import com.tingyu.tongmeng.edu.service.edu.filter.CommentFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @Author essionshy
 * @Create 2020/11/4 15:27
 * @Version tongmeng-edu
 */
@Configuration
public class AppConfig {

    /**
     * 课程评论过滤器，对评论内容进行合法性检验
     * @return
     */
    @Bean
    public FilterRegistrationBean<CommentFilter> commentFilter(){
        FilterRegistrationBean<CommentFilter> commentFilter = new FilterRegistrationBean<>();
        commentFilter.setFilter(new CommentFilter());
        commentFilter.setUrlPatterns(Arrays.asList("/edu/comment/front/save"));
        return commentFilter;
    }

}
