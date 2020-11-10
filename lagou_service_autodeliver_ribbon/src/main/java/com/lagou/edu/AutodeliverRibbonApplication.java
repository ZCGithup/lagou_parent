package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangchi
 * @create 2020-11-09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启feignClient客户端功能

//@EnableHystrix         开启Hystrix功能
@EnableCircuitBreaker  //开启熔断器 （通用）

//@SpringCloudApplication 综合性注解 = @SpringBootApplication + @EnableDiscoveryClient + @EnableCircuitBreaker
public class AutodeliverRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutodeliverRibbonApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
