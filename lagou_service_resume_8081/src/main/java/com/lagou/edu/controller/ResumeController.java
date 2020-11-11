package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
       // return  resume.getIsOpenResume();
        return 8081;
    }

    @GetMapping("/openStateTimeout/{userId}")
    public Integer getResumeDefaultStateTimeOut(@PathVariable Long userId){

        Resume resume = resumeService.findDefaultResumeByUserID(userId);
        // return  resume.getIsOpenResume();
        return port;
    }
}
