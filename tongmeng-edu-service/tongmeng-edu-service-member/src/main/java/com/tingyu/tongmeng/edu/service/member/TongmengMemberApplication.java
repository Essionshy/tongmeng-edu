package com.tingyu.tongmeng.edu.service.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/10/30 13:39
 * @Version tongmeng-edu
 */
@SpringBootApplication
@ComponentScan("com.tingyu.tongmeng.edu")
@MapperScan(basePackages = {"com.tingyu.tongmeng.edu.service.member.dao"})
@EnableDiscoveryClient
public class TongmengMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(TongmengMemberApplication.class,args);
    }
}
