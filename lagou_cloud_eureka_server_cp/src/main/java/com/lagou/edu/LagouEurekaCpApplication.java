package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
@SpringBootApplication
//声明当前工程为 EurekaServer
@EnableEurekaServer
public class LagouEurekaCpApplication {

    public static void main(String[] args) {
        SpringApplication.run(LagouEurekaCpApplication.class,args);
    }
}
