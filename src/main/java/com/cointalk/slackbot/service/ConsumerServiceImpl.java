package com.cointalk.slackbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class ConsumerServiceImpl implements ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    private final test tt;

    public ConsumerServiceImpl(test tt) {
        this.tt = tt;
    }

//    @KafkaListener(topics = "slack-topic", groupId = "slack-group", containerFactory = "pushEntityKafkaListenerContainerFactory")
    @KafkaListener(topics = "slack-topic", groupId = "slack-group")
    public void getMessageToSlack(@Payload String message, @Headers MessageHeaders messageHeaders) {
        logger.info(String.format("#### 카프카 소비자 데이터 ### : %s \n### 카프카 헤더 정보 ### : %s", message, messageHeaders));
        Mono<String> lectureMono = Mono.just(message)
                .doOnNext(tt::slackSendMessage)
                ;
        lectureMono.subscribe();
    }

}