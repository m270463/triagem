package com.triagem.api;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@ConditionalOnProperty(name = "classificador.modo", havingValue = "http")
public class ClassificadorHttp implements Classificador {

    private final RestClient client;

    public ClassificadorHttp(@Value("${classificador.url}") String url) {
        SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
        f.setConnectTimeout(Duration.ofSeconds(2));
        f.setReadTimeout(Duration.ofSeconds(10));
        this.client = RestClient.builder().baseUrl(url).requestFactory(f).build();
    }

    @Override
    public Classificacao classificar(String texto) {
        return client.post()
                .uri("/classificar")
                .body(Map.of("texto", texto))
                .retrieve()
                .body(Classificacao.class);
    }
}