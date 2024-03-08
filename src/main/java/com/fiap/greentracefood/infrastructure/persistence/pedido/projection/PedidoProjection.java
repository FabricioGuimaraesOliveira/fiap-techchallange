package com.fiap.greentracefood.infrastructure.persistence.pedido.projection;



import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;

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