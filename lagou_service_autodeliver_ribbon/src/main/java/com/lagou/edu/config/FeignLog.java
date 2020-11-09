package com.lagou.edu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign 日志级别
 * @author zhangchi
 * @create 2020-11-09
 */
@Configuration
public class FeignLog {

    @Bean
    Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }
}
