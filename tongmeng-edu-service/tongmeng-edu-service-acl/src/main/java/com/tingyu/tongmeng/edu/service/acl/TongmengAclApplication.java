package com.tingyu.tongmeng.edu.service.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/11/2 16:32
 * @Version tongmeng-edu
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.tingyu.tongmeng.edu.service.acl.dao"})
@ComponentScan(basePackages = {"com.tingyu.tongmeng.edu"})
public class TongmengAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(TongmengAclApplication.class,args);
    }
}
