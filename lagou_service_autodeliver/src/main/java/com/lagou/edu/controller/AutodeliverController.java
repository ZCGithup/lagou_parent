package com.lagou.edu.controller;

import com.netflix.appinfo.InstanceInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhangchi
 * @create 2020-11-08
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;


    /**
     * RestTemplate 方式
     * @param userId
     * @return
     */
    @GetMapping("checkstate/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId){
        String url = "http://localhost:8080/resume/openState/";

        Integer state = restTemplate.getForObject(url + userId, Integer.class);

        return state;
    }


    @GetMapping("checkAndBegin/{userId}")
    public Integer findResumeOpenStates(@PathVariable Long userId){
       // String url = "http://localhost:8080/resume/openState/";

        //根据服务名称获取服务实例信息
        List<ServiceInstance> instanceList = discoveryClient.getInstances("LAGOU_SERVICE_RESUME");
        //取出第一个实例 不考虑负载
        ServiceInstance serviceInstance = instanceList.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        //拼接远程地址
        String url = "http://" + host + ":" + port + "/resume/openState/" + userId;

        Integer state = restTemplate.getForObject(url, Integer.class);

        return state;
    }


}
