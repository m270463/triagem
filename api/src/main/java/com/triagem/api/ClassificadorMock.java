package com.triagem.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "classificador.modo", havingValue = "mock", matchIfMissing = true)
public class ClassificadorMock implements Classificador{

    @Override
    public Classificacao classificar(String texto) {
        String t = texto.toLowerCase();

        if (t.contains("cancelar")) {
            return new Classificacao("cancelamento", "alta", "negativo","resumo","sugestao");
        }

        if (t.contains("cobrado") || t.contains("fatura") || t.contains("boleto")) {
            return new Classificacao("cobranca", "media", "negativo","resumo","sugestao");
        }

        if (t.contains("obrigado") || t.contains("parabéns")) {
            return new Classificacao("elogio", "baixa", "positivo","resumo","sugestao");
        }

        return new Classificacao("suporte", "media", "neutro","resumo","sugestao");
    }
}
