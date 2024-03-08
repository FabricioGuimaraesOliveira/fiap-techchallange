package com.fiap.greentracefood.usecases.pagamento;


import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;

public class PagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;

    public PagamentoUseCase(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    public Pagamento consultarPorPedido(String codigoPedido) {
        return pagamentoGateway.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public void registrarPagamento(String codigoPedido, StatusPagamento statusPagamento) {
        Pagamento pagamento = pagamentoGateway.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        pagamento.setStatus(statusPagamento);
        pagamentoGateway.salvar(pagamento);
    }
}
