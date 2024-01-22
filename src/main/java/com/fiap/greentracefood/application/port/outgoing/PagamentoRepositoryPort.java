package com.fiap.greentracefood.application.port.outgoing;

import com.fiap.greentracefood.application.domain.Pagamento;

import java.util.Optional;

public interface PagamentoRepositoryPort {
    Optional<Pagamento> consultarPorPedido(String codigoPedido);
    void salvar(Pagamento pagamento);
}
