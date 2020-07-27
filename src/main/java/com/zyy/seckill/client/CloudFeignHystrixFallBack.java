package com.zyy.seckill.client;

import org.springframework.stereotype.Component;

@Component
public class CloudFeignHystrixFallBack implements CloudFeignHystrixClient {
    @Override
    public String getFeign() {
        return "不好意思 feignClient 出错了！";
    }
}
