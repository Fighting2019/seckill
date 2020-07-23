package com.zyy.seckill;

import com.zyy.seckill.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIncr implements Runnable {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run() {
        System.out.println(redisUtils.hashIncr("testHashSequence", "supplierSequence", 1));
    }


    @Test
    public void test() throws Exception {
        throw new Exception("测试统一异常处理");
    }
}
