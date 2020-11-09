package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
    public RestTemplate GetRestTemplate(){
        return new RestTemplate();
    }

}
