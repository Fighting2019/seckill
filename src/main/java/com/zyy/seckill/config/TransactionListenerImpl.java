package com.zyy.seckill.config;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransactionListenerImpl implements TransactionListener {
    public static Map<String,Object> map = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        LocalTransactionState lts = LocalTransactionState.UNKNOW;
        /**
         * 处理本地事务
         */
        String transactionId = message.getTransactionId();
        map.put(transactionId,"1");
        lts = LocalTransactionState.COMMIT_MESSAGE;
        return lts;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String transactionId = messageExt.getTransactionId();
        if (Objects.nonNull(map.get(transactionId))){
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        return LocalTransactionState.UNKNOW;
    }
}
