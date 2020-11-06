package com.tingyu.tongmeng.edu.service.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author essionshy
 * @Create 2020/11/1 21:36
 * @Version tongmeng-edu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.tingyu.tongmeng.edu"})
@EnableScheduling
public class TongmengJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TongmengJobApplication.class,args);
    }


}
