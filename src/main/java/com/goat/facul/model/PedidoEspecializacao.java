package com.goat.facul.model;

import jakarta.persistence.*;


@Entity
public class PedidoEspecializacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Servidor servidor;

    @ManyToOne
    private Especializacao especializacao;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private String motivoReprovacao;

    public PedidoEspecializacao(Long id, Servidor servidor, Especializacao especializacao, StatusPedido status, String motivoReprovacao) {
        this.id = id;
        this.servidor = servidor;
        this.especializacao = especializacao;
        this.status = status;
        this.motivoReprovacao = motivoReprovacao;
    }

    public PedidoEspecializacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Especializacao getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(Especializacao especializacao) {
        this.especializacao = especializacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public String getMotivoReprovacao() {
        return motivoReprovacao;
    }

    public void setMotivoReprovacao(String motivoReprovacao) {
        this.motivoReprovacao = motivoReprovacao;
    }
    // Getters e setters, construtores, etc.
}

