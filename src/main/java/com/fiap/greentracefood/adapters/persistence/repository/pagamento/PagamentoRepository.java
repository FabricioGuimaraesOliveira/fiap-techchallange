package com.fiap.greentracefood.adapters.persistence.repository.pagamento;

import com.fiap.greentracefood.adapters.persistence.entity.PagamentoEntity;
import com.fiap.greentracefood.application.domain.Pagamento;
import com.fiap.greentracefood.application.port.outgoing.PagamentoRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class PagamentoRepository implements PagamentoRepositoryPort {
    private  final SpringPagamentoRepository springPagamentoRepository;
    private final ModelMapper modelMapper;

    public PagamentoRepository(SpringPagamentoRepository springPagamentoRepository, ModelMapper modelMapper) {
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
