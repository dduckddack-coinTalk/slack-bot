package com.cointalk.slackbot.service;


import com.cointalk.slackbot.entity.SlackMessageData;

public interface ProducerService {

    // 슬렉 메시지 전송 테스트
    void sendMessageToSlack(SlackMessageData slackMessageData);
}
