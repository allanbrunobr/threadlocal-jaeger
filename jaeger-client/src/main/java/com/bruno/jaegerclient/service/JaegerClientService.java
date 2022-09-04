package com.bruno.jaegerclient.service;

import com.bruno.jaegerclient.localthread.LocalThreadTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JaegerClientService {

    private WebClient webClient;

    @Value("${jaeger-server.host}")
    private String jaegerServerHost;

    public JaegerClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> get(Integer id) {

        Mono<String> resultado = webClient.get()
                .uri("http://" + jaegerServerHost + id)
                .retrieve()
                .bodyToMono(String.class);



        return resultado;
    }

}