package com.fiap.greentracefood.domain.entity.pedido.gateway;


import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.domain.entity.pedido.model.PedidoResumido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PedidoGateway {

    Optional<PedidoResumido> consultarPorCodigo(String codigoPedido);
    Page<Pedido> listarPedidosPaginadosPorCpf(String cpf, Pageable pageable);
    Page<PedidoResumido> listarPedidosPorStatus(StatusPedido status, Pageable pageable);
    Page<Pedido> listarTodosPedidosPaginados(Pageable pageable);
    Optional<Pedido> detalharPorCodigo(String codigoPedido);
    Pedido salvar(Pedido pedido);



}
