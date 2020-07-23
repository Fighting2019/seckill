package com.zyy.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisListener extends MessageListenerAdapter {

    @Autowired
    private StringRedisSerializer stringRedisSerializer;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] messageBody = message.getBody();
        byte[] messageChannel = message.getChannel();
        String body = stringRedisSerializer.deserialize(messageBody);
        String channel = stringRedisSerializer.deserialize(messageChannel);
        System.out.println("<receive> msg: " + body + "||| channel: " + channel);
    }
}
