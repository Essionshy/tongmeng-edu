package com.tingyu.tongmeng.edu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/10/27 9:52
 * @Version tongmeng-edu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.tingyu.tongmeng.edu"})
@EnableDiscoveryClient
public class AliyunOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliyunOssApplication.class,args);
    }
}
