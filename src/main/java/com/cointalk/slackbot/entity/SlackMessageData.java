package com.cointalk.slackbot.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SlackMessageData {

    private String message;

    private String channel;
}
