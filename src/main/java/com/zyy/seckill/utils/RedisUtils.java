package com.zyy.seckill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private HashOperations hashOperations;
    @Autowired
    private ListOperations listOperations;
    @Autowired
    private SetOperations setOperations;
    @Autowired
    private ZSetOperations zSetOperations;


    public Long hashIncr(String key,String hashKey,long increment){
        return hashOperations.increment(key,hashKey,increment);
    }

    /*public void sub(){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.addMessageListener();
    }*/

}
