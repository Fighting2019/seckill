package com.zyy.seckill.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "seckill",fallback = CloudFeignHystrixFallBack.class)
public interface CloudFeignHystrixClient {

    @GetMapping("/hystrixTest")
    String getFeign();
}
