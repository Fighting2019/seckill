package com.zyy.seckill;

import com.alibaba.fastjson.JSON;
import com.zyy.seckill.service.SeckillService;
import com.zyy.seckill.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class SeckillApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations valueOperations;

    @Autowired
    private HashOperations hashOperations;

    @Autowired
    private RedisUtils redisUtils;

    private static final String SEQUENCE_KEY = "testHashSequence";

    private static final String SEQUENCE_HASH_KEY = "supplierSequence";

    @Autowired
    private SeckillService seckillService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedis(){
        String str = valueOperations.get("1099").toString();
        System.out.println(str);
    }


    @Test
    void testGetRedisSequence(){
        RedisAtomicLong redisSequence = new RedisAtomicLong("testRedisSequence", redisTemplate.getConnectionFactory());
        //redisSequence.
        long sequence = redisSequence.getAndIncrement();
        System.out.println(String.format("当前值:%s", sequence));
        //return sequence;
    }

    @Test
    void testSetRedisSequence(){
        RedisAtomicLong redisSequence = new RedisAtomicLong("testRedisSequence2", redisTemplate);
        redisSequence.set(1L);
        //redisSequence.expire();
    }

    @Test
    void testRedisHash(){
        //hashOperations.put("testHashSequence","supplierSequence", JSON.toJSONString(1));
        System.out.println(this.hashIncr(SEQUENCE_KEY,SEQUENCE_HASH_KEY,1));
    }


    @Test
    long hashIncr(String key,String hashKey,long increment){
        return hashOperations.increment(key,hashKey,increment);
    }

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    @Test
    void testIncr2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i=0; i<10; i++) {
            executorService.execute(()->{
                seckillService.testIncr();
                countDownLatch.countDown();
            });
//            seckillService.testIncr();
        }
        countDownLatch.await();
    }

    /*@Test
    void testExpier(){
        redisTemplate.expire()
    }*/

    @Test
    public void test() throws Exception {
        throw new Exception("测试统一异常处理");
    }

}
