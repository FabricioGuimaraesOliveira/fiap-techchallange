package com.fiap.greentracefood.usecases.solicitacao;

import com.fiap.greentracefood.domain.entity.cliente.validator.CpfValidator;
import com.fiap.greentracefood.domain.entity.solicitacao.gateway.SolicitacaoExclusaoGateway;
import com.fiap.greentracefood.domain.entity.solicitacao.model.SolicitacaoExclusao;


public class SolicitacaoExclusaoUseCase {

    final private SolicitacaoExclusaoGateway solicitacaoExclusaoGateway;

    public SolicitacaoExclusaoUseCase(SolicitacaoExclusaoGateway solicitacaoExclusaoGateway) {
        this.solicitacaoExclusaoGateway = solicitacaoExclusaoGateway;

    }

    public SolicitacaoExclusao cadastrar(SolicitacaoExclusao solicitacaoExclusao) {
    	solicitacaoExclusao.setCpf(CpfValidator.sanitizar(solicitacaoExclusao.getCpf()));
        
    	return solicitacaoExclusaoGateway.salvar(solicitacaoExclusao);
    }
}