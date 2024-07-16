package com.goat.facul.DTO;

import java.time.LocalDate;
import java.util.List;

public class ServidorDTO {
    private long id;
    private String cpf;
    private String email;
    private String matricula;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String tipo;
    private List<EspecializacaoDTO> especializacoes;

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<EspecializacaoDTO> getEspecializacoes() {
        return especializacoes;
    }

    public void setEspecializacoes(List<EspecializacaoDTO> especializacoes) {
        this.especializacoes = especializacoes;
    }
}
