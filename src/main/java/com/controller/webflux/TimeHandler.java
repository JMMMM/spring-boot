package com.controller.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class TimeHandler {

    private ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    public Mono<ServerResponse> time(ServerRequest serverRequest) {
        SimpleDateFormat sdf = threadLocal.get();
        if (sdf == null) {
            threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            sdf = threadLocal.get();
        }
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is " + sdf.format(new Date())), String.class);
    }
}
