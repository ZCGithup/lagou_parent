package com.lagou.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangchi
 * @create 2020-11-09
 */
@FeignClient(value = "lagou-service-resume")
@RequestMapping("/resume")
public interface ResumeFeginClient {

    @GetMapping("/openState/{userId}")
    public Integer getResumeDefaultState(@PathVariable("userId") Long userId);

}
