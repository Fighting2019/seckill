package com.zyy.seckill.config;


import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.clientconfig.FeignClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DefaultFeignConfig {

    /*public FeignClientConfigurer feignClientConfigurer(){

        return new FeignClientConfigurer() {
            @Override
            public boolean inheritParentConfiguration() {
                return false;
            }
        };
    }*/

    /*@Bean
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }*/
}
