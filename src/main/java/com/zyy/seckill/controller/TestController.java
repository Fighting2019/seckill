package com.zyy.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TransactionMQProducer transactionMQProducer;

    @GetMapping("/testExceptionHandler")
    public void testExceptionHandler() throws Exception {
        throw new Exception("测试统一异常处理");
    }

    @RequestMapping()
    public void sendTransactionMq(){
        Message message = new Message();
        message.setTopic("base");
        message.setTags("topic");
        message.setBody("hello".getBytes());
        try {
            transactionMQProducer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Integer a = Integer.valueOf (1);
        Integer b = new Integer(1);
        System.out.println(a == b);
    }
}
