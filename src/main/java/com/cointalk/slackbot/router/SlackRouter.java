package com.cointalk.slackbot.router;

import com.cointalk.slackbot.handler.SlackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class SlackRouter {

    @Bean
    public RouterFunction<ServerResponse> route(SlackHandler slackHandler) {
        return RouterFunctions
                .route(POST("/test"), slackHandler::sendMessage)
                ;
    }
}
