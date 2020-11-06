package com.tingyu.tongmeng.edu.service.acl.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author essionshy
 * @Create 2020/10/25 21:27
 * @Version tongmeng-edu
 */

@Configuration
@MapperScan(basePackages = {"com.tingyu.tongmeng.edu.service.edu.dao"})
public class MybatisPlusConfig {

    /**
     * Mybatis-Plus 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor PaginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
