package com.fiap.greentracefood.adapters.web.response.pedido;

import com.fiap.greentracefood.application.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Data
@EqualsAndHashCode
@ToString
public class PedidoResumidoResponseDTO {

    private String codigo;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private String statusPagamento;
    private String nomeCliente;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;

}
