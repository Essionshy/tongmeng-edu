package com.tingyu.tongmeng.edu.service.rms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/10/29 16:37
 * @Version tongmeng-edu
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.tingyu.tongmeng.edu.service.rms.dao")
@ComponentScan("com.tingyu.tongmeng")
public class ResourceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceManagementApplication.class,args);
    }
}
