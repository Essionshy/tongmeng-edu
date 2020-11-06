package com.tingyu.tongmeng.edu.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author essionshy
 * @Create 2020/10/30 10:26
 * @Version tongmeng-edu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan("com.tingyu.tongmeng.edu")
public class AliyunSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunSmsApplication.class,args);
    }
}
