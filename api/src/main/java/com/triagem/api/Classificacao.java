package com.triagem.api;

import jakarta.validation.constraints.NotBlank;

public record Classificacao(
    @NotBlank String categoria,
    @NotBlank String prioridade,
    @NotBlank String sentimento,
    @NotBlank String resumo,
    @NotBlank String sugestaoResposta
) {}
