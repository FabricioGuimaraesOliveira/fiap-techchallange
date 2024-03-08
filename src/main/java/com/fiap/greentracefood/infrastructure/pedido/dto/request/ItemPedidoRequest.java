package com.fiap.greentracefood.infrastructure.pedido.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoRequest {
    @Schema(description = "ID do Produto", example = "1")
    @NotNull(message = "ID do Produto não pode ser nulo")
    @Positive(message = "ID do Produto deve ser maior que zero")
    private Long produtoId;

    @Schema(description = "Quantidade do Produto", example = "1")
    @NotNull(message = "Quantidade não pode ser nula")
    @Positive(message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    @Schema(description = "Observação do Pedido", example = "Acrescentar mais queijo")
    private String observacao;
}