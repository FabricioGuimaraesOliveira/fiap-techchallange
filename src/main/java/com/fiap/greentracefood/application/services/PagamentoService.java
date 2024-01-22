package com.fiap.greentracefood.application.services;

import com.fiap.greentracefood.application.domain.Pagamento;
import com.fiap.greentracefood.application.enums.StatusPagamento;
import com.fiap.greentracefood.application.port.incoming.PagamentoUseCase;
import com.fiap.greentracefood.application.port.outgoing.PagamentoRepositoryPort;

public class PagamentoService implements PagamentoUseCase {

    private PagamentoRepositoryPort pagamentoRepositoryPort;
    public PagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort) {
        this.pagamentoRepositoryPort = pagamentoRepositoryPort;
    }
    @Override
    public Pagamento consultarPorPedido(String codigoPedido) {
        return pagamentoRepositoryPort.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    @Override
    public void registrarPagamento(String codigoPedido, StatusPagamento statusPagamento) {
        Pagamento pagamento = pagamentoRepositoryPort.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        pagamento.setStatus(statusPagamento);
        pagamentoRepositoryPort.salvar(pagamento);
    }
}
