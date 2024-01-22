package com.fiap.greentracefood.adapters.web.response.pedido;

import com.fiap.greentracefood.adapters.web.response.cliente.ClienteResponseDTO;
import com.fiap.greentracefood.adapters.web.response.item.ItemPedidoResponseDTO;
import com.fiap.greentracefood.application.domain.Pagamento;
import com.fiap.greentracefood.application.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoResponseDTO {

    @Schema(example = "05d2b496-9751-4d4d-9875-71444271fc25")
    private String codigo;

    @Schema(description = "Cliente", implementation = ClienteResponseDTO.class)
    private ClienteResponseDTO cliente;

    @Schema(example = "CRIADO", description = "Status do pedido")
    private StatusPedido status;

    @Schema(example = "116.00")
    private BigDecimal subtotal;

    @Schema(example = "7.50")
    private BigDecimal taxaFrete;

    @Schema(example = "123.50")
    private BigDecimal valorTotal;

    @Schema(example = "2020-04-11T00:03:25Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "2020-04-11T00:07:39Z")
    private OffsetDateTime dataConfirmacao;

    @Schema(example = "2020-04-11T00:05:06Z")
    private OffsetDateTime dataCancelamento;

    @Schema(example = "2020-04-11T00:45:41Z")
    private OffsetDateTime dataEntrega;

    @Schema(example = "2020-04-11T00:45:41Z")
    private OffsetDateTime dataFinalizacao;

    @Schema(description = "Itens do pedido", implementation = ItemPedidoResponseDTO.class)
    private List<ItemPedidoResponseDTO> itens;

    @Schema(description = "Pagamento do pedido", implementation = Pagamento.class)
    private Pagamento pagamento;





}
