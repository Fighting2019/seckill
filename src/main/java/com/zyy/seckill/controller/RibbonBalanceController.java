package com.zyy.seckill.controller;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonBalanceController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.cloud.client.ip-address}")
    private String hostMessage;

    @Value("${server.port}")
    private String port;

    @GetMapping("/ribbonProducer")
    public String getMessage(){
        return hostMessage + ":" + port;
    }

    @GetMapping("/getribbon")
    public String getProducer(){
        return restTemplate.getForObject("http://seckill/ribbonProducer",String.class);
    }


    @GetMapping("/hystrixTest")
    public String getHystrix() throws InterruptedException {
        Thread.sleep(2000); //此处模拟处理超时
        return Joiner.on(":").join("你好我是订单系统。。。",hostMessage,port);
    }
}
