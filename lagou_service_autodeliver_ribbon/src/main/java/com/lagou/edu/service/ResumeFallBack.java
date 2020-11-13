package com.lagou.edu.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 降级回退方法定义
 * @author zhangchi
 * @create 2020-11-11
 */
@Component
public class ResumeFallBack implements ResumeFeginClient{
    @Override
    public Integer getResumeDefaultState(Long userId) {
        return null;
    }


    @Override
    public Integer openStateTimeout(Long userId) {
        return -9000;
    }


    @Override
    public Integer checkFeignHystrixsFallback(Long userId) {
        return -8;
    }
}
