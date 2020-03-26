package com.example.plusdemo.consumer;

import com.example.plusdemo.event.SaveUserEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author VingKing
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "save_user",consumerGroup = "my-consumer-save_user")
public class SaveUserConsumer implements RocketMQListener<SaveUserEvent> {

    @Override
    public void onMessage(SaveUserEvent saveUserEvent) {
        log.info("received SaveUserEvent: {}", saveUserEvent);
    }
}
