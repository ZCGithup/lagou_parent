package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author zhangchi
 * @create 2020-11-11
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine //开启turbine功能
public class HystrixsTurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixsTurbineApplication.class,args);
    }
}
