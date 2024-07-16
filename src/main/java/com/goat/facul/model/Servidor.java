package com.goat.facul.model;

import com.goat.facul.model.Especializacao;
import com.goat.facul.model.TipoServidor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String email;
    private String matricula;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;

    @Enumerated(EnumType.STRING)
    private TipoServidor tipo; // Professor, Técnico

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "servidor_especializacao",
            joinColumns = { @JoinColumn(name = "servidor_id") },
            inverseJoinColumns = { @JoinColumn(name = "especializacao_id") }
    )
    private Set<Especializacao> especializacoes = new HashSet<>();

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public TipoServidor getTipo() {
        return tipo;
    }

    public void setTipo(TipoServidor tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<Especializacao> getEspecializacoes() {
        return especializacoes;
    }

    public void setEspecializacoes(Set<Especializacao> especializacoes) {
        this.especializacoes = especializacoes;
    }
}
