package com.tingyu.tongmeng.edu.service.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/10/31 16:16
 * @Version tongmeng-edu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.tingyu.tongmeng.edu"})
public class TongmengPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TongmengPayApplication.class,args);
    }
}
