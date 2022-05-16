package com.cointalk.slackbot.service;

import org.springframework.messaging.MessageHeaders;

public interface ConsumerService {

    // 슬렉메시지 전송
    void slackTopicConsumer(String String, MessageHeaders messageHeaders);
    void newsTopicConsumer(String String, MessageHeaders messageHeaders);
    void errorTopicConsumer(String String, MessageHeaders messageHeaders);
}
