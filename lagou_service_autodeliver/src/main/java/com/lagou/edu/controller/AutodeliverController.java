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

    /**
     * 服务列表从注册中心拉取 再通过RestTemplate调用
     * @param userId
     * @return
     */
    @GetMapping("checkAndBegin/{userId}")
    public Integer findResumeOpenStates(@PathVariable Long userId){
       // String url = "http://localhost:8080/resume/openState/";

        //todo  从eurekaServer中获取我们关注的服务实例信息。进而获取到服务的ip，port，接口信息

        //1、根据服务名称获取服务实例信息
        List<ServiceInstance> instanceList = discoveryClient.getInstances("LAGOU_SERVICE_RESUME");
        //2、取出第一个实例（负载均衡） 不考虑负载
        ServiceInstance serviceInstance = instanceList.get(0);
        //3、从元数据中获取host port信息
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        //4、拼接远程地址
        String url = "http://" + host + ":" + port + "/resume/openState/" + userId;
        //5、远程调用
        Integer state = restTemplate.getForObject(url, Integer.class);

        return state;
    }

    /**
     * 服务列表从注册中心拉取 再通过RestTemplate调用
     * @param userId
     * @return
     */
    @GetMapping("checkRibbon/{userId}")
    public Integer findResumeOpenStatesByRibbon(@PathVariable Long userId){


        //todo  RestTemplate整合了Ribbon负载均衡   加上 @LoadBalanced注解
        String url = "http://LAGOU_SERVICE_RESUME/resume/openState/" + userId;
        //1、ribbon 会根据服务名称，再通过负载均衡算法选择一个实例，替换成ip + 端口号

        //2、远程调用
        Integer state = restTemplate.getForObject(url, Integer.class);

        return state;
    }

}
