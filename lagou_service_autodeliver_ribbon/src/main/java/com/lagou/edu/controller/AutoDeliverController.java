package com.lagou.edu.controller;

import com.lagou.edu.service.ResumeFeginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangchi
 * @create 2020-11-09
 */
@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {


    @Autowired
    private ResumeFeginClient resumeFeginClient;

    /**
     * 使用 Fegin 远程调用
     * @param userId
     * @return
     */
    @GetMapping("checkstate/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId){

        Integer state = resumeFeginClient.getResumeDefaultState(userId);

        return state;
    }

}
