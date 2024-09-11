package com.fiap.greentracefood.infrastructure.solicitacao.gateway;

import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.greentracefood.domain.entity.solicitacao.gateway.SolicitacaoExclusaoGateway;
import com.fiap.greentracefood.domain.entity.solicitacao.model.SolicitacaoExclusao;
import com.fiap.greentracefood.infrastructure.persistence.solicitacao.SolicitacaoExclusaoEntity;
import com.fiap.greentracefood.infrastructure.persistence.solicitacao.SpringSolicitacaoExclusaoRepository;

public class SolicitacaoExclusaoDataBaseRepository implements SolicitacaoExclusaoGateway {
	private final SpringSolicitacaoExclusaoRepository solicitacaoRepository;
	private final ModelMapper modelMapper;

	public SolicitacaoExclusaoDataBaseRepository(SpringSolicitacaoExclusaoRepository solicitacaoRepository, ModelMapper modelMapper) {
		this.solicitacaoRepository = solicitacaoRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	@Transactional
	public SolicitacaoExclusao salvar(SolicitacaoExclusao solicitacaoExclusao) {
		var solicitacaoEntity = solicitacaoRepository.saveAndFlush(modelMapper.map(solicitacaoExclusao, SolicitacaoExclusaoEntity.class));
		
		solicitacaoEntity.setNumeroAcompanhamento(solicitacaoEntity.getId());
        solicitacaoEntity = solicitacaoRepository.saveAndFlush(solicitacaoEntity);
		
		return modelMapper.map(solicitacaoEntity, SolicitacaoExclusao.class);
	}
}