package com.zyy.seckill.config;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

public class SeckillHystrixCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    private Long id;

    public SeckillHystrixCommand(RestTemplate restTemplate){
        super(setter());
        this.restTemplate = restTemplate;
        //this.id = id;
    }

    private static Setter setter(){
        // 服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("seckill");
        //
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("seckill");
        // 线程池组
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("seckill_pool");
        // 设置线程池属性
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(50).
                withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);

        // 设置 Hystrix 相关属性
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter().
                withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                .withExecutionTimeoutEnabled(true) // 是否开启超时
                .withExecutionTimeoutInMilliseconds(3000) // 设置断路超时时间
                .withCircuitBreakerEnabled(true); // 是否开启断路

        return Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

    }

    @Override
    protected String run() throws Exception {
        //此处 RestTemplate 加了 @LoadBalancer 注解，集成了 consul 注册中心支持负载均衡
        return restTemplate.getForObject("http://seckill/hystrixTest",String.class);
    }
}