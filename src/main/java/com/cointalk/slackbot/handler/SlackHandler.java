package com.cointalk.slackbot.handler;

import com.cointalk.slackbot.entity.SlackMessageData;
import com.cointalk.slackbot.service.ProducerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class SlackHandler {
    private final ProducerService producerService;

    public SlackHandler(ProducerService producerService) {
        this.producerService = producerService;
    }

    public Mono<ServerResponse> sendMessage(ServerRequest request) {
        Mono<SlackMessageData> slackMono = request.bodyToMono(SlackMessageData.class)
                .doOnNext(producerService::sendMessageToSlack) // 카프카 프로듀서에 메시지 데이터 전달
                .subscribeOn(Schedulers.boundedElastic())
                .log();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(slackMono, SlackMessageData.class)
                .onErrorResume(error -> ServerResponse.badRequest().build())
                ;
    }

}
