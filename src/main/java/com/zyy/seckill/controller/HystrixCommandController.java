package com.zyy.seckill.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyy.seckill.config.SeckillHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Hystrix 线程隔离 Demo
 */
@RestController
public class HystrixCommandController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/testHystrix")
    public String testHystrix(){
        return new SeckillHystrixCommand(restTemplate).execute();
    }
}
