package com.goat.facul.DTO;

import com.goat.facul.model.StatusPedido;

public class PedidoEspecializacaoDTO {

    private Long id;
    private Long idServidor;
    private Long idEspecializacao;
    private String status;
    private String motivoReprovacao;

    // Construtores, getters e setters

    public PedidoEspecializacaoDTO() {
    }

    public PedidoEspecializacaoDTO(Long id, Long idServidor, Long idEspecializacao, StatusPedido status, String motivoReprovacao) {
        this.id = id;
        this.idServidor = idServidor;
        this.idEspecializacao = idEspecializacao;
        this.status = String.valueOf(status);
        this.motivoReprovacao = motivoReprovacao;
    }

    public String getMotivoReprovacao() {
        return motivoReprovacao;
    }

    public void setMotivoReprovacao(String motivoReprovacao) {
        this.motivoReprovacao = motivoReprovacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdEspecializacao() {
        return idEspecializacao;
    }

    public void setIdEspecializacao(Long idEspecializacao) {
        this.idEspecializacao = idEspecializacao;
    }

    public Long getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Long idServidor) {
        this.idServidor = idServidor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

