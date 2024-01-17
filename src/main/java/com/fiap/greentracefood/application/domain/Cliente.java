package com.fiap.greentracefood.application.domain;

import java.time.OffsetDateTime;

public class Cliente {
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public long getId() {
        return id;
    }

    private long id;
    private String nome;
    private String email;
    private String senha;
    private OffsetDateTime dataCadastro;
}
