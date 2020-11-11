package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hytrixs 仪表盘项目
 * @author zhangchi
 * @create 2020-11-11
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard //开启仪表盘
public class HytrixsDashBoradApplication {

    public static void main(String[] args) {
        SpringApplication.run(HytrixsDashBoradApplication.class,args);
    }

}
