package com.zyy.seckill.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
@Configuration
public class RocketmqProducerConfig {

    @Value("${rocketmq.nameServerAddr}")
    private String nameServerAddr;

    @Value("${rocketmq.producer.groupName}")
    private String producerGroupName;

    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize;

    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;

    @Value("${rocketmq.transaction.producer.corePoolSize:4}")
    private int corePoolSize;

    @Value("${rocketmq.transaction.producer.maximumPoolSize:8}")
    private int maximumPoolSize;

    @Value("${rocketmq.transaction.producer.keepAliveSecond:120}")
    private int keepAliveTime;

    @Value("${rocketmq.transaction.producer.queueCapacity:2000}")
    private int queueCapacity;

    @Bean
    public DefaultMQProducer getDefaultMQProducer(){
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(nameServerAddr);
        defaultMQProducer.setMaxMessageSize(maxMessageSize);
        defaultMQProducer.setSendMsgTimeout(sendMsgTimeout);
        defaultMQProducer.setProducerGroup(producerGroupName);
        defaultMQProducer.setInstanceName("test");
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return defaultMQProducer;
    }

    @Bean
    public TransactionMQProducer getTransactionMQProducer(){
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer();
        transactionMQProducer.setNamesrvAddr(nameServerAddr);
        transactionMQProducer.setMaxMessageSize(maxMessageSize);
        transactionMQProducer.setSendMsgTimeout(sendMsgTimeout);
        transactionMQProducer.setProducerGroup(producerGroupName);
        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueCapacity),(r) -> {
            Thread thread = new Thread(r);
            return thread;
        });
        transactionMQProducer.setExecutorService(executorService);
        TransactionListener transactionListener = new TransactionListenerImpl();
        transactionMQProducer.setTransactionListener(transactionListener);
        try {
            transactionMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return transactionMQProducer;
    }

}
