package com.triagem.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NovaMensagemRequest(
    @NotBlank String canal,
    @NotBlank String cliente,
    @NotBlank @Size(max = 2000) String texto
){}
