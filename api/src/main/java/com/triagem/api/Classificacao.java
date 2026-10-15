package com.triagem.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record Classificacao(
    @NotBlank String categoria,
    @NotBlank String prioridade,
    @NotBlank String sentimento,
    @NotBlank String resumo,
    @NotBlank @JsonProperty("sugestao_resposta") String sugestaoResposta
) {}
