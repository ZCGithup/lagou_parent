package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AutodeliverApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication.class,args);
    }


    @Bean
    @LoadBalanced //使用Ribbon为RestTemplate赋予负载均衡的能力
    public RestTemplate GetRestTemplate(){
        return new RestTemplate();
    }

}
