package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Pagamento;

public interface PagamentoUseCase {
    public Pagamento consultaPagamento(Long idPedido);
    public void registra(Pagamento pagamento);
}
