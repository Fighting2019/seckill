package com.zyy.seckill.config;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

public class SeckillHystrixCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    private Long id;

    public SeckillHystrixCommand(RestTemplate restTemplate,Long id){
        super(setter());
        this.restTemplate = restTemplate;
        this.id = id;
    }

    private static Setter setter(){
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("seckill");
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("seckill");
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("seckill_pool");

        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(50).
                withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter().
                withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                .withExecutionTimeoutEnabled(false);

        return Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

    }

    @Override
    protected String run() throws Exception {
        return null;
    }
}