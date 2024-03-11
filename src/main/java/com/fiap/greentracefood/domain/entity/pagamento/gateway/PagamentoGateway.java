package com.fiap.greentracefood.domain.entity.pagamento.gateway;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;

import java.util.Optional;

public interface PagamentoGateway {
   Optional<Pagamento> consultarPorPedido(String codigoPedido);
    void salvar(Pagamento pagamento);
}
