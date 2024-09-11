package com.fiap.greentracefood.infrastructure.persistence.solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringSolicitacaoExclusaoRepository  extends JpaRepository<SolicitacaoExclusaoEntity, Long>, JpaSpecificationExecutor<SolicitacaoExclusaoEntity> {
}