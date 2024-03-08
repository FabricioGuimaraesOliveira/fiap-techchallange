package com.fiap.greentracefood.infrastructure.pedido.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode
@ToString
@Schema(description = "DTO para representação resumida de um pedido")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResumidoResponseDTO {

    @Schema(example = "05d2b496-9751-4d4d-9875-71444271fc25", description = "Código do pedido")
    private String codigo;

    @Schema(example = "123.50", description = "Valor total do pedido")
    private BigDecimal valorTotal;

    @Schema(description = "Status do pedido")
    private StatusPedido status;

    @Schema(example = "APROVADO", description = "Status do pagamento associado ao pedido")
    private StatusPagamento statusPagamento;

    @Schema(description = "Nome do cliente associado ao pedido (opcional)")
    private String nomeCliente;

    @Schema(description = "Data e hora de criação do pedido")
    private OffsetDateTime dataCriacao;

    @Schema(description = "Data e hora de confirmação do pedido")
    private OffsetDateTime dataConfirmacao;

    @Schema(description = "Data e hora de cancelamento do pedido")
    private OffsetDateTime dataCancelamento;

    @Schema(description = "Data e hora de entrega do pedido")
    private OffsetDateTime dataEntrega;

}
