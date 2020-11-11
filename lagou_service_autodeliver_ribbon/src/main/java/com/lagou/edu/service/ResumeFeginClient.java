package com.lagou.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangchi
 * @create 2020-11-09
 */
@FeignClient(value = "lagou-service-resume",fallback = ResumeFallBack.class,path = "resume")
//@RequestMapping("/resume") //支持hystrix回退会报错 解决 path = "resume"
public interface ResumeFeginClient {

    @GetMapping("/openState/{userId}")
    public Integer getResumeDefaultState(@PathVariable("userId") Long userId);

    /**
     * 测试 feign + hystrixs 回退方法
     * @param userId
     * @return
     */
    @GetMapping("checkFeignHystrixsFallback")
    public Integer checkFeignHystrixsFallback(@PathVariable("userId") Long userId);

}
