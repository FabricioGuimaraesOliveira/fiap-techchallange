package com.fiap.greentracefood.adapters.web.response.item;

import com.fiap.greentracefood.application.domain.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@EqualsAndHashCode
@ToString
public class ItemPedidoResponseDTO {
    @Schema(description = "Preco Unitario",example = "19.0")
    private BigDecimal precoUnitario;
    @Schema(description = "Preco Total",example = "50.0")
    private BigDecimal precoTotal;
    @Schema(description = "Quantidade",example = "1")
    private Integer quantidade;
    @Schema(description = "Observacao",example = "Sem Cebola")
    private String observacao;
    private Produto produto;
}
