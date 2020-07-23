package com.zyy.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @RequestMapping("/ribbonProducer")
    public String getMessage(){
        return hostMessage + ":" + port;
    }

    @RequestMapping("/getribbon")
    public String getProducer(){
        return restTemplate.getForObject("http://seckill/ribbonProducer",String.class);
    }
}
