package com.triagem.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/mensagens")
public class MensagemController {
    private final MensagemRepository repositorio;

    public MensagemController(MensagemRepository repositorio){
        this.repositorio = repositorio;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mensagem criar(@Valid @RequestBody NovaMensagemRequest req){
        Mensagem m = new Mensagem();
        m.setCanal(req.canal());
        m.setCliente(req.cliente());
        m.setTexto(req.texto());
        return m;
    }

    @GetMapping
    public List<Mensagem> listar(){
        return repositorio.findAll();
    }

}
