package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;



    @GetMapping("/openState/{userId}")
    public Integer getResumeDefaultState(@PathVariable Long userId){
        Resume resume = resumeService.findDefaultResumeByUserID(userId);
        //return  resume.getIsOpenResume();
        return 8080;
    }

    @GetMapping("/openStateTimeout/{userId}")
    public Integer openStateTimeout(@PathVariable Long userId){

        //模拟处理超时
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Resume resume = resumeService.findDefaultResumeByUserID(userId);
        return port;
    }


    /**
     * feign + hystrix 回退方法测试
     * @param userId
     * @return
     */
    @GetMapping("checkFeignHystrixsFallback")
    public Integer checkFeignHystrixsFallback(@PathVariable Long userId){

        //模拟处理超时
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
