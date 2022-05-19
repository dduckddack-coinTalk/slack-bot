package com.cointalk.slackbot.service;

import com.cointalk.slackbot.entity.SlackMessageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessageToSlack(SlackMessageData slackMessageData) {
        String topic = slackMessageData.getChannel()+"-topic";
        logger.info(String.format("**** 카프카 데이터 생산 : **** TOPIC: %s , MESSAGE: %s", topic, slackMessageData.getMessage()));
        this.kafkaTemplate.send(topic, slackMessageData.getMessage());
    }
}
