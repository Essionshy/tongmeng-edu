package com.tingyu.tongmeng.edu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author essionshy
 * @Create 2020/11/3 8:08
 * @Version tongmeng-edu
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TongmengGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TongmengGatewayApplication.class, args);
    }
}
