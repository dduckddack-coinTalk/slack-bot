package com.cointalk.slackbot.service;

import com.cointalk.slackbot.entity.SlackMessageData;
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

    private final SlackMessageHandler slackMessageHandler;

    public ConsumerServiceImpl(SlackMessageHandler slackMessageHandler) {
        this.slackMessageHandler = slackMessageHandler;
    }

    // slack-topic 구독으로 Slack 채널에 메시지 전송
    @KafkaListener(topics = "slack-topic", groupId = "slack-group")
    public void slackTopicConsumer(@Payload String message, @Headers MessageHeaders messageHeaders) {
        logger.info(String.format("#### 카프카 소비자 데이터 ### : %s \n### 카프카 헤더 정보 ### : %s", message, messageHeaders));
        Mono<SlackMessageData> topicMono = Mono.just(new SlackMessageData(message, "error"))
                .doOnNext(slackMessageHandler::slackSendMessage)
                ;
        topicMono.subscribe();
    }

    // error-topic 구독으로 Slack 채널에 메시지 전송
    @KafkaListener(topics = "error-topic", groupId = "error-group")
    public void errorTopicConsumer(@Payload String message, @Headers MessageHeaders messageHeaders) {
        logger.info(String.format("#### 카프카 소비자 데이터 ### : %s \n### 카프카 헤더 정보 ### : %s", message, messageHeaders));
        Mono<SlackMessageData> topicMono = Mono.just(new SlackMessageData(message, "error"))
                .doOnNext(slackMessageHandler::slackSendMessage)
                ;
        topicMono.subscribe();
    }

    // news-topic 구독으로 Slack 채널에 메시지 전송
    @KafkaListener(topics = "news-topic", groupId = "news-group")
    public void newsTopicConsumer(@Payload String message, @Headers MessageHeaders messageHeaders) {
        logger.info(String.format("#### 카프카 소비자 데이터 ### : %s \n### 카프카 헤더 정보 ### : %s", message, messageHeaders));
        Mono<SlackMessageData> topicMono = Mono.just(new SlackMessageData(message, "news"))
                .doOnNext(slackMessageHandler::slackSendMessage)
                ;
        topicMono.subscribe();
    }

}