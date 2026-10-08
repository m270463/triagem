package com.triagem.api;

import org.springframework.stereotype.Component;

@Component
public class ClassificadorTeste implements Classificador{

    @Override
    public Classificacao classificar(String texto) {
        String t = texto.toLowerCase();

        if (t.contains("cancelar")) {
            return new Classificacao("cancelamento", "alta", "negativo");
        }
        if (t.contains("cobrado") || t.contains("fatura") || t.contains("boleto")) {
            return new Classificacao("cobranca", "media", "negativo");
        }
        if (t.contains("obrigado") || t.contains("parabéns")) {
            return new Classificacao("elogio", "baixa", "positivo");
        }


        return new Classificacao("suporte", "media", "neutro");


    }
    
}
