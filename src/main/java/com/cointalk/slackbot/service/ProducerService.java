package com.cointalk.slackbot.service;


public interface ProducerService {

    //강의 신규생성시 이벤트 카프카에 전송
//    void sendLectureData(Lecture lecture);

    // 슬렉 메시지 전송 테스트
    void sendMessageToSlack(String message);
}
