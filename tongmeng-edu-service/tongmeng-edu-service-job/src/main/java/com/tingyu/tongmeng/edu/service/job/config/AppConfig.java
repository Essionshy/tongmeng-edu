package com.tingyu.tongmeng.edu.service.job.config;

import com.tingyu.tongmeng.edu.service.job.filter.MyFilter;
import com.tingyu.tongmeng.edu.service.job.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.Arrays;

/**
 * @Author essionshy
 * @Create 2020/11/4 16:45
 * @Version tongmeng-edu
 */
@Configuration
public class AppConfig {

    //注册三大组件

    @Bean
    public ServletRegistrationBean myServlet(){

        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(),"/hello");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter(){

        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/do"));
        return registrationBean;
    }


}
