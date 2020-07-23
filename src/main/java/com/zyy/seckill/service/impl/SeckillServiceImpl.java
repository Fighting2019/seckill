package com.zyy.seckill.service.impl;

import com.zyy.seckill.service.SeckillService;
import com.zyy.seckill.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService{

    /*@Resource
    private RedisUtils redisUtils;*/


    @Override
    public void testIncr() {
        System.out.println("test");
    }

    @Override
    public void distLock() {
        RedissonClient redissonClient = Redisson.create();
        RLock lock = redissonClient.getLock("myLock");
        lock.lock();
        lock.unlock();
        RLock redLock = redissonClient.getRedLock(lock);
        redLock.lock();
        redLock.unlock();
    }
    /*public void testIncr() {
        System.out.println(redisUtils.hashIncr("testHashSequence","supplierSequence", 1));
    }*/

}
