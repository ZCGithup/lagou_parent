package com.lagou.edu.controller;

import com.lagou.edu.service.ResumeFeginClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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


    @Autowired
    private RestTemplate restTemplate;
    /**
     * 模拟服务提供者处理超时  使用Hystrix熔断
     * @param userId
     * @return
     */
    //使用@HystrixCommand进行熔断控制
    @HystrixCommand(
            //commandProperties 是熔断的细节配置属性
            commandProperties ={
                    //设置最大处理时间
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="2000" )
            }
    )
    @GetMapping("checkstateTimeOut/{userId}")
    public Integer checkResumeStateTimeOut(@PathVariable Long userId){

        String url = "http://lagou-service-resume/resume/openStateTimeout/" + userId;
        //2、远程调用
        Integer state = restTemplate.getForObject(url, Integer.class);
        return state;
    }

    /**
     * 场景应用：
     *
     * 1、服务提供者处理超时，熔断，返回错误信息
     * 2、服务提供者出现异常直接抛出异常信息
     *
     * 以上信息都会返回到消费者，很多时候消费者接受到异常/错误信息，不想返回到它的上游去
     *
     * 例如： 用户微服务  ---> 注册微服务  -----> 优惠券微服务
     *          1、登记注册
     *          2、分发优惠券 (并不是核心业务) 如果这里优惠券微服务出现了错误/异常,直接返回给用户并不是很友好
     *                  我们可以返回一些兜底数据 返回默认值。
     *
     * 一般我们要熔断方法是一些非核心的业务。
     */


    /**
     * 模拟服务提供者处理超时  使用Hystrix熔断----支持回退
     * @param userId
     * @return
     */
    //使用@HystrixCommand进行熔断控制
    @HystrixCommand(
            //commandProperties 是熔断的细节配置属性
            commandProperties ={
                    //设置最大处理时间
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="2000" )
            },
            fallbackMethod = "myFallBack" //指定回退方法
    )
    @GetMapping("checkstateTimeOutFallback/{userId}")
    public Integer checkResumeStateTimeOutFallback(@PathVariable Long userId){

        String url = "http://lagou-service-resume/resume/openStateTimeout/" + userId;
       // String url = "http://localhost:8081/resume/openStateTimeout/" + userId;
        //2、远程调用
        Integer state = restTemplate.getForObject(url, Integer.class);
        return state;
    }

    /**
     * 定义回退方法 返回兜底数据
     * 方法的参数，和方法值与方法保持一致
     */
    public Integer myFallBack(Long userId){
        return -1;//返回兜底数据
    }




    /**
     * 模拟服务提供者处理超时  使用Hystrix熔断 ---支持回退---使用舱壁模式
     * @param userId
     * @return
     */
    //使用@HystrixCommand进行熔断控制
    @HystrixCommand(
            //线程池标识 要保存唯一 否则就共用一个线程池
            threadPoolKey = "checkResumeStateTimeOutFallback",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "1"), //线程数
                    @HystrixProperty(name = "maxQueueSize",value = "20")//等待队列长度
            },
            //commandProperties 是熔断的细节配置属性
            commandProperties ={
                    //设置最大处理时间
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value ="2000" )
            },
            fallbackMethod = "myFallBack1" //指定回退方法
    )
    @GetMapping("checkstateTimeOutFallback1/{userId}")
    public Integer checkResumeStateTimeOutFallback1(@PathVariable Long userId){

        String url = "http://lagou-service-resume/resume/openStateTimeout/" + userId;
        //2、远程调用
        Integer state = restTemplate.getForObject(url, Integer.class);
        return state;
    }

    /**
     * 定义回退方法 返回兜底数据
     * 方法的参数，和方法值与方法保持一致
     */
    public Integer myFallBack1(Long userId){
        return -1;//返回兜底数据
    }
}
