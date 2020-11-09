package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("checkstate/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId){
        String url = "http://localhost:8080/resume/openState/";

        Integer state = restTemplate.getForObject(url + userId, Integer.class);

        return state;
    }

}
