package com.cointalk.slackbot.service;

import org.springframework.messaging.MessageHeaders;

public interface ConsumerService {

    // 강의 신규생성 이벤트를 받으면 새로 생성된 강의에 디폴트 강사 배정 (강사아이디 : 999, 강사이름 : 신규강사)
//    void matchingDefaultTeacher(Lecture lecture, MessageHeaders messageHeaders);

    // 슬렉메시지 전송
    void getMessageToSlack(String String, MessageHeaders messageHeaders);
}
