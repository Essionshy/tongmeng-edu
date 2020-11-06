package com.tingyu.tongmeng.edu.service.sas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/11/1 17:25
 * @Version tongmeng-edu
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.tingyu.tongmeng.edu.service.sas.dao"})
@ComponentScan(basePackages = {"com.tingyu.tongmeng.edu"})
@EnableDiscoveryClient
@EnableFeignClients
public class StatisticalAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticalAnalysisApplication.class,args);
    }
}
