package com.tingyu.tongmeng.edu.service.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/10/31 15:30
 * @Version tongmeng-edu
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.tingyu.tongmeng.edu.service.order.dao"})
@ComponentScan("com.tingyu.tongmeng.edu")
@EnableFeignClients
public class TongmengOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TongmengOrderApplication.class,args);
    }
}
