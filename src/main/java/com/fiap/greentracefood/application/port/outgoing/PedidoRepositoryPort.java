package com.fiap.greentracefood.application.port.outgoing;

import com.fiap.greentracefood.application.domain.Pedido;

public interface PedidoRepositoryPort {
    Pedido salvar(Pedido pedido);
    Pedido buscarPedidoPorId(Long id);
}
