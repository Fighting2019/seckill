package com.zyy.seckill.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DefaultRibbonConfig {

    @Bean
    public IPing getIPing(){
        return new PingUrl();
    }

    @Bean
    public IRule getIRule(){
        return new RandomRule();
    }
}
