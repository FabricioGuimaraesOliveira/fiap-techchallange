package com.fiap.greentracefood.adapters.persistence.repository.pedido;

import com.fiap.greentracefood.adapters.persistence.entity.PedidoEntity;
import com.fiap.greentracefood.application.builders.PedidoResumidoBuilder;
import com.fiap.greentracefood.application.domain.Pedido;
import com.fiap.greentracefood.application.domain.PedidoResumido;
import com.fiap.greentracefood.application.enums.StatusPedido;
import com.fiap.greentracefood.application.port.outgoing.PedidoRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoRepository implements PedidoRepositoryPort {
    public PedidoRepository(SpringPedidoRepository pedidoRepository, ModelMapper modelMapper) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
    }
    private final SpringPedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;
    @Override
    @Transactional()
    public Pedido salvar(Pedido pedido) {
        var pedidoEntity = pedidoRepository.saveAndFlush(modelMapper.map(pedido, PedidoEntity.class));
        return modelMapper.map(pedidoEntity, Pedido.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PedidoResumido> buscarResumoPedidoPorCodigo(String codigoPedido) {
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

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPedidoPorCodigo(String codigoPedido) {
        Optional<PedidoEntity> pedidoEntity = pedidoRepository.findByCodigo(codigoPedido);
        return pedidoEntity.map(entity -> modelMapper.map(entity, Pedido.class));
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Pedido> listarPedidosPaginadosPorCpf(String cpf, Pageable pageable) {
        Page<PedidoEntity> pedidosPage = pedidoRepository.findByClienteCpf(cpf, pageable);
        return pedidosPage.map(entity -> modelMapper.map(entity, Pedido.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PedidoResumido> listarPedidosPorStatus(StatusPedido status, Pageable pageable) {
        Page<PedidoProjection> pedidosPage = pedidoRepository.findPedidosByStatus(status, pageable);
        return pedidosPage.map(entity -> modelMapper.map(entity, PedidoResumido.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarPedidosPorCpfEDataCadastro(String cpf, OffsetDateTime dataCadastroInicio, OffsetDateTime dataCadastroFim) {
        List<PedidoEntity> pedidosEntities = pedidoRepository.findByCpfAndDataCadastro(cpf, dataCadastroInicio, dataCadastroFim);
        return pedidosEntities.stream()
                .map(entity -> modelMapper.map(entity, Pedido.class))
                .collect(Collectors.toList());
    }
}
