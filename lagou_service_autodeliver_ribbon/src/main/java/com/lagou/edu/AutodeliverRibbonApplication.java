package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangchi
 * @create 2020-11-09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启feignClient客户端功能
public class AutodeliverRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutodeliverRibbonApplication.class,args);
    }
}
