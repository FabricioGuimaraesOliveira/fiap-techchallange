package com.fiap.greentracefood.infrastructure.pagamento.gateway;
import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.infrastructure.persistence.pagamento.PagamentoEntity;


import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;
import com.fiap.greentracefood.infrastructure.persistence.pagamento.SpringPagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public class PagamentoDataBaseRepository implements PagamentoGateway {
    private  final SpringPagamentoRepository springPagamentoRepository;
    private final ModelMapper modelMapper;

    public PagamentoDataBaseRepository(SpringPagamentoRepository springPagamentoRepository, ModelMapper modelMapper) {
        this.springPagamentoRepository = springPagamentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pagamento> consultarPorPedido(String codigoPedido) {
        return springPagamentoRepository.findByPedidoCodigo(codigoPedido).map(pagamentoEntity -> modelMapper.map(pagamentoEntity, Pagamento.class));
    }


    @Override
    @Transactional()
    public void salvar(Pagamento pagamento) {
        springPagamentoRepository.save(modelMapper.map(pagamento, PagamentoEntity.class));
    }
}
