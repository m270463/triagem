package com.triagem.api;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "mensagens")
public class Mensagem {
    
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;

    private String canal;
    private String cliente;

    @Column (columnDefinition= "TEXT")
    private String texto;

    private String categoria;
    private String prioridade;
    private String sentimento;
    private boolean classificada;
    private String status = "novo";
    private LocalDateTime criadoEm = LocalDateTime.now();
    private String sugestaoResposta;
    private String resumo;

    
    public Long getId() {
        return id;
    }
    public String getCanal() {
        return canal;
    }
    public String getCliente() {
        return cliente;
    }
    public String getTexto() {
        return texto;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getPrioridade() {
        return prioridade;
    }
    public String getSentimento() {
        return sentimento;
    }
    public boolean isClassificada() {
        return classificada;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCanal(String canal) {
        this.canal = canal;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    public void setSentimento(String sentimento) {
        this.sentimento = sentimento;
    }
    public void setClassificada(boolean classificada) {
        this.classificada = classificada;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
    public String getSugestaoResposta() {
        return sugestaoResposta;
    }
    public String getResumo() {
        return resumo;
    }
    public void setSugestaoResposta(String sugestaoResposta) {
        this.sugestaoResposta = sugestaoResposta;
    }
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    

}
