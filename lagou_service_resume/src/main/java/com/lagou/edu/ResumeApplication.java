package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
//@EnableEurekaClient //开启Eurek Client （独有）
@EnableDiscoveryClient //开启注册中心客户端 （通用版，注册到Eureka,Nacos等） 可以代替@EnableEurekaClient
public class ResumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication.class,args);
    }
}
