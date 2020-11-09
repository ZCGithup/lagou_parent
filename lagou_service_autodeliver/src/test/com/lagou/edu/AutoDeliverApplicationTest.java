package com.lagou.edu;

import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhangchi
 * @create 2020-11-09
 */
@SpringBootTest(classes=AutodeliverApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AutoDeliverApplicationTest {

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 测试实例列表元数据
     */
    @Test
    public void testInstanceMeteData(){
        //1、根据服务名称获取服务实例信息
        List<ServiceInstance> instanceList = discoveryClient.getInstances("LAGOU_SERVICE_RESUME");

        for (int i = 0; i < instanceList.size(); i++) {
            ServiceInstance serviceInstance =  instanceList.get(i);
            System.out.println(serviceInstance);
        }
    }

}
