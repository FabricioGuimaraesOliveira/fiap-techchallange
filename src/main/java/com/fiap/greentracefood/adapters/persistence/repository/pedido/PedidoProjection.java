package com.fiap.greentracefood.adapters.persistence.repository.pedido;

import com.fiap.greentracefood.application.enums.StatusPagamento;
import com.fiap.greentracefood.application.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface PedidoProjection {
    String getCodigo();
    BigDecimal getValorTotal();
    StatusPedido getStatus();
    StatusPagamento getStatusPagamento();
    String getNomeCliente();
    OffsetDateTime getDataCriacao();
    OffsetDateTime getDataConfirmacao();
    OffsetDateTime getDataCancelamento();
    OffsetDateTime getDataEntrega();
}