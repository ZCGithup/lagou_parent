package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author zhangchi
 * @create 2020-11-12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class,args);
    }
}
