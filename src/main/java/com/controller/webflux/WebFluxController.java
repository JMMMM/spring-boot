package com.controller.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * 反应式
 * curl localhost:8080/time
 */
@Configuration
public class WebFluxController {

    @Autowired
    private TimeHandler timeHandler;


    @Bean
    public RouterFunction<ServerResponse> webflux() {
        return route(GET("/time"), req -> timeHandler.time(req));
    }


}
