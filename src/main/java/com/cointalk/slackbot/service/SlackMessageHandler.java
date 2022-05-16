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
//    @Value(value = "${slack.token}")
//    String token;
//    @Value(value = "${slack.channel.monitor}")
//    String channel;
//    public void slackSendMessage(String message){
//        try{
//            Slack slack = Slack.getInstance();
//            slack.methods(token).chatPostMessage(req -> req.channel("news").text(message));
//        } catch (SlackApiException | IOException e) {
////            log.error(e.getMessage());
//        }
//    }

    @Value(value = "${slack.token}")
    String token;

    public void slackSendMessage(SlackMessageData slackMessageData){
        try{
            Slack slack = Slack.getInstance();
            slack.methods(token).chatPostMessage(req -> req
                    .channel(slackMessageData.getChannel())
                    .text(slackMessageData.getMessage()));
        } catch (SlackApiException | IOException e) {
//            log.error(e.getMessage());
        }
    }
}
