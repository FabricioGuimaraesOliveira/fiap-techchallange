package com.fiap.greentracefood.infrastructure.pedido.gateway;


import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.domain.entity.pedido.model.PedidoResumido;
import com.fiap.greentracefood.infrastructure.pedido.builder.PedidoResumidoBuilder;
import com.fiap.greentracefood.infrastructure.persistence.pedido.PedidoEntity;
import com.fiap.greentracefood.infrastructure.persistence.pedido.projection.PedidoProjection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class PedidoDatabaseRepository implements PedidoGateway {

    private final SpringPedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    public PedidoDatabaseRepository(SpringPedidoRepository pedidoRepository, ModelMapper modelMapper) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional()
    public Pedido salvar(Pedido pedido) {
        var pedidoEntity = pedidoRepository.saveAndFlush(modelMapper.map(pedido, PedidoEntity.class));
        return modelMapper.map(pedidoEntity, Pedido.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PedidoResumido> consultarPorCodigo(String codigoPedido) {
        Optional<PedidoProjection> projectionOptional = pedidoRepository.findPedidoResumidoByCodigo(codigoPedido);
        return projectionOptional.map(entity -> new PedidoResumidoBuilder()
                .codigo(entity.getCodigo())
                .dataCriacao(entity.getDataCriacao())
                .dataConfirmacao(entity.getDataConfirmacao())
                .dataCancelamento(entity.getDataCancelamento())
                .nomeCliente(entity.getNomeCliente())
                .status(entity.getStatus())
                .statusPagamento(entity.getStatusPagamento())
                .valorTotal(entity.getValorTotal())
                .build());
    }


    @Transactional(readOnly = true)
    public Page<Pedido> listarPedidosPaginadosPorCpf(String cpf, Pageable pageable) {
        Page<PedidoEntity> pedidosPage = pedidoRepository.findByClienteCpf(cpf, pageable);
        return pedidosPage.map(entity -> modelMapper.map(entity, Pedido.class));
    }

    @Transactional(readOnly = true)
    public Page<PedidoResumido> listarPedidosPorStatus(StatusPedido status, Pageable pageable) {
        Page<PedidoProjection> pedidosPage = pedidoRepository.findPedidosByStatus(status, pageable);
        return pedidosPage.map(entity -> modelMapper.map(entity, PedidoResumido.class));
    }
    @Transactional(readOnly = true)
    public Page<Pedido> listarTodosPedidosPaginados(Pageable pageable) {
        return pedidoRepository.findPedidosOrderByStatus(List.of(StatusPedido.RECEBIDO,StatusPedido.PREPARANDO,StatusPedido.PRONTO),pageable).map(entity -> modelMapper.map(entity, Pedido.class));
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Pedido> detalharPorCodigo(String codigoPedido) {
        Optional<PedidoEntity> pedidoEntity = pedidoRepository.findByCodigo(codigoPedido);
        return pedidoEntity.map(entity -> modelMapper.map(entity, Pedido.class));
    }

}
