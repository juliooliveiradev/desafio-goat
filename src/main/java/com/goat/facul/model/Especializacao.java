package com.goat.facul.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Especializacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String area;

    @Enumerated(EnumType.STRING)
    private TipoEspecializacao tipo;

    private int cargaHoraria;

    @Column(name = "valor_total_custo", nullable = false)
    private BigDecimal valorTotal;

    @ManyToMany(mappedBy = "especializacoes")
    @JsonIgnore
    private Set<Servidor> servidores = new HashSet<>();

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public TipoEspecializacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoEspecializacao tipo) {
        this.tipo = tipo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Set<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(Set<Servidor> servidores) {
        this.servidores = servidores;
    }

    public void setAprovada(boolean b) {
    }

    public void setMotivoIndeferimento(String motivo) {
    }
}
