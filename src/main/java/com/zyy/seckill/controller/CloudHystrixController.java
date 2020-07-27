package com.zyy.seckill.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.zyy.seckill.client.CloudFeignHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
public class CloudHystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private CloudFeignHystrixClient cloudFeignHystrixClient;

    @HystrixCommand(fallbackMethod = "cloudHystrixFallBack",
            groupKey = "cloudHystrix", //命令所属的服务组
            commandKey = "cloudHystrix", //命令的key值
            threadPoolKey = "cloudHystrixThreadPool", //线程池名称
            commandProperties = {
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED,value = "true"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED,value = "true"),
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT,value = "1000")
            },
            threadPoolProperties = {@HystrixProperty(name = HystrixPropertiesManager.CORE_SIZE,value = "2")}
    )
    @GetMapping("/getCloudHystrix")
    public String getStringResponse(){

        return restTemplate.getForObject("http://seckill/hystrixTest",String.class);
    }

    /**
     * 降级接口
     */
    public String cloudHystrixFallBack(){
        return "不好意思出错了";
    }


    @GetMapping("/feignHystrixTest")
    public String feignClientTest(){
        return cloudFeignHystrixClient.getFeign();
    }
}