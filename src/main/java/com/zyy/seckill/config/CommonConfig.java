package com.zyy.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
public class CommonConfig {

    @Bean
    public void pingScheduled(){
        Executors.newScheduledThreadPool(2).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run(){
                System.out.println("定时任务线程池，模拟心跳检测");
            }
        }, 1000*30, 1000*30, TimeUnit.MILLISECONDS);
    }
}
