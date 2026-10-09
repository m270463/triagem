package com.triagem.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/mensagens")
public class MensagemController {
    private final MensagemRepository repositorio;
    private final Classificador classificador;

    public MensagemController(MensagemRepository repositorio, Classificador classificador){
        this.repositorio = repositorio;
        this.classificador = classificador;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mensagem criar(@Valid @RequestBody NovaMensagemRequest req){
        Mensagem m = new Mensagem();
        m.setCanal(req.canal());
        m.setCliente(req.cliente());
        m.setTexto(req.texto());
        Classificacao c = classificador.classificar(req.texto());
        m.setPrioridade(c.prioridade());
        m.setSentimento(c.sentimento());
        m.setCategoria(c.categoria());
        m.setClassificada(true);
        return repositorio.save(m);
    }

    @GetMapping
    public PagedModel<Mensagem> listar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String prioridade,
            @RequestParam(required = false) String categoria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, Math.min(size, 100),
                Sort.by("criadoEm").descending());

        Page<Mensagem> resultado = repositorio.buscar(status, prioridade, categoria, pageable);
        return new PagedModel<>(resultado);
    }

}
