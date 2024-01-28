package com.fiap.greentracefood.application.port.outgoing;

import com.fiap.greentracefood.application.domain.Pedido;
import com.fiap.greentracefood.application.domain.PedidoResumido;
import com.fiap.greentracefood.application.enums.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedido salvar(Pedido pedido);
    Optional<PedidoResumido> buscarResumoPedidoPorCodigo(String codigoPedido);
    Optional<Pedido> buscarPedidoPorCodigo(String codigoPedido);
    Page<Pedido> listarPedidosPaginadosPorCpf(String cpf, Pageable pageable);
    Page<PedidoResumido> listarPedidosPorStatus(StatusPedido status, Pageable pageable);
    Page<Pedido> listarTodosPedidosPaginados(Pageable pageable);

}
