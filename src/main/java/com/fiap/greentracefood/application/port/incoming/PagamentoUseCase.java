package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Pagamento;
import com.fiap.greentracefood.application.enums.StatusPagamento;

public interface PagamentoUseCase {
   Pagamento consultarPorPedido(String codigoPedido);
    void registrarPagamento(String codigoPedido, StatusPagamento statusPagamento);
}
