package com.fiap.greentracefood.infrastructure.pedido.builder;


import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pedido.model.PedidoResumido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PedidoResumidoBuilder {

    private final PedidoResumido pedidoResumido;

    public PedidoResumidoBuilder() {
        this.pedidoResumido = new PedidoResumido();
    }

    public PedidoResumidoBuilder codigo(String codigo) {
        pedidoResumido.setCodigo(codigo);
        return this;
    }

    public PedidoResumidoBuilder valorTotal(BigDecimal valorTotal) {
        pedidoResumido.setValorTotal(valorTotal);
        return this;
    }

    public PedidoResumidoBuilder status(StatusPedido status) {
        pedidoResumido.setStatus(status);
        return this;
    }

    public PedidoResumidoBuilder statusPagamento(StatusPagamento statusPagamento) {
        pedidoResumido.setStatusPagamento(statusPagamento);
        return this;
    }

    public PedidoResumidoBuilder nomeCliente(String nomeCliente) {
        pedidoResumido.setNomeCliente(nomeCliente);
        return this;
    }

    public PedidoResumidoBuilder dataCriacao(OffsetDateTime dataCriacao) {
        pedidoResumido.setDataCriacao(dataCriacao);
        return this;
    }

    public PedidoResumidoBuilder dataConfirmacao(OffsetDateTime dataConfirmacao) {
        pedidoResumido.setDataConfirmacao(dataConfirmacao);
        return this;
    }
    public PedidoResumidoBuilder dataCancelamento(OffsetDateTime dataCancelamento) {
        pedidoResumido.setDataCancelamento(dataCancelamento);
        return this;
    }
    public PedidoResumidoBuilder dataEntrega(OffsetDateTime dataEntrega) {
        pedidoResumido.setDataEntrega(dataEntrega);
        return this;
    }
    public PedidoResumido build() {
        return pedidoResumido;
    }
}
