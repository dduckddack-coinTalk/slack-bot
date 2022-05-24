package com.cointalk.slackbot.service;


import com.cointalk.slackbot.entity.SlackMessageData;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SlackMessageHandler {

    @Value(value = "${slack.token}")
    String token;

    // Slack 토큰으로 채널에 에러 메시지 전송
    public void slackSendMessage(SlackMessageData slackMessageData){
        try{
            Slack slack = Slack.getInstance();
            slack.methods(token).chatPostMessage(req -> req
                    .channel(slackMessageData.getChannel())
                    .text(slackMessageData.getMessage()));
        } catch (SlackApiException | IOException e) {
            e.printStackTrace();
        }
    }
}
