package com.fiap.greentracefood.domain.entity.solicitacao.model;

import java.time.OffsetDateTime;

public class SolicitacaoExclusao {
	private Long id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private OffsetDateTime dataSolicitacao;
    private Long numeroAcompanhamento;
	    
    public SolicitacaoExclusao() {
	}

	public SolicitacaoExclusao(Long id, String cpf, String nome, String endereco, String telefone,
			OffsetDateTime dataSolicitacao, Long numeroAcompanhamento) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.dataSolicitacao = dataSolicitacao;
		this.numeroAcompanhamento = numeroAcompanhamento;
	}

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public OffsetDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(OffsetDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Long getNumeroAcompanhamento() {
		return numeroAcompanhamento;
	}

	public void setNumeroAcompanhamento(Long numeroAcompanhamento) {
		this.numeroAcompanhamento = numeroAcompanhamento;
	}
}