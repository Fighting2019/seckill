package com.zyy.seckill.controller;

import com.google.common.collect.Lists;
import com.zyy.seckill.domains.DeduceStock;
import com.zyy.seckill.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RefreshScope
@RestController
public class SeckillController {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Value("${name}")
    private String name;

    @Value("${spring.cloud.client.ip-address}")
    private String host;

    @GetMapping("/configTest")
    public String configTest(){
        return host + "====" +name;
    }

    @GetMapping("/publish")
    public void testSub(){
        //redisMessageListenerContainer.addMessageListener(new );
        for (int i = 0; i < 10; i++) {
            redisTemplate.convertAndSend("topic1", "hello " + i);
        }
    }


    @GetMapping("/deduceStock")
    public String deduceStock(){
        StringBuilder sb = new StringBuilder();
        sb.append("if (redis.call('exists', KEYS[1]) == 1) then");
        sb.append("    local stock = tonumber(redis.call('get', KEYS[1]));");
        sb.append("    local num = tonumber(ARGV[1]);");
        sb.append("    if (stock == -1) then");
        sb.append("        return -1;");
        sb.append("    end;");
        sb.append("    if (stock >= num) then");
        sb.append("        return redis.call('incrby', KEYS[1], 0 - num);");
        sb.append("    end;");
        sb.append("    return -2;");
        sb.append("end;");
        sb.append("return -3;");
        String STOCK_LUA = sb.toString();
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        //redisScript.setLocation(new ClassPathResource("test.lua"));
        redisScript.setScriptText(STOCK_LUA);
        redisScript.setResultType(Long.class);
        List<String> keys = new ArrayList<>();
        keys.add("stock");
        /*List<String> params = new ArrayList<>();
        params.add("10");*/
        String[] p = new String[1];
        p[0] = new String("10");
        return redisTemplate.execute(redisScript, keys, p).toString();
        //return "success:" + status;
    }
}
