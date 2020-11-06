package com.tingyu.tongmeng.edu.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/10/25 19:02
 * @Version tongmeng-edu
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tingyu.tongmeng"})//
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class TongmengApplication {

    public static void main(String[] args) {
        SpringApplication.run(TongmengApplication.class,args);
    }
}
