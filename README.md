# slack-bot
서버에 장애 발생시 Kafka에 전달된 에러메시지를 Slack에 전송하여 에러 메시지 알림을 제공하는 서버

### 배경(Background)

* 각 서버의 장애 발생시 이를 개발자가 인지 할 수 있도록 슬렉에 메시지를 전송하는 서버입니다. 또 만약 슬렉 서버에 문제가 생겨 장애 발생알림이 가지 않았더라도 차후에 메시지를 받아 볼 수 있도록 카프카 토픽을 이용했습니다.

### 목표(Goals)

* 서버에 장애 발생시 슬렉 에러채널에 메시지 전송
* 카프카 토픽 구독을 사용하여 만약 슬렉서버도 문제가 생겨서 메시지 전송이 안됬더라도 차후에 처리되어 슬렉 알림이 갈 수 있도록 구현 

### 계획 (Plan)
* 카프카와 연동하여 다른 서버에서 보내는 에러 메시지 구독
* 구독한 에러 메시지 토픽이 도착하면 이를 처리해서 슬렉 에러메시지 채널에 전송


### HowTo Setting


#### 1. Kafka 설치 및 구동
1. 카프카 설치
2. 주키퍼 구동
- bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
3. 카프카 구동
- bin/kafka-server-start.sh -daemon config/server.properties
4. 에러메시지 보낼 토픽 생성

#### 2. Slack 봇 생성 및 채널구독
1. Slack 가입 후 봇 생성하고 토큰값 등록
2. 에러메시지 전송할 채널에 봇 등록
3. aplication.yml 설정파일에 토큰값 입력


#### aplication.yml 설정

```yml
server:
  port: 8090

spring:
  kafka:
    consumer:
      bootstrap-servers: 카프카 consumer 서버 주소
    producer:
      bootstrap-servers: 카프카 producer 서버 주소

slack:
  token: Slack 봇 토큰값
  channel:
    monitor: error

```





