package com.hand.cache.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Created by zhangpengfei on 2019/1/17 15:46.
 */
@Component
@Slf4j
public class SayHelloMsgReceiver {

    @KafkaListener(id = "hello", topics = "hello")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()){
            String name = (String)kafkaMessage.get();
            log.info("Hello, " + name);
        }
    }
}
