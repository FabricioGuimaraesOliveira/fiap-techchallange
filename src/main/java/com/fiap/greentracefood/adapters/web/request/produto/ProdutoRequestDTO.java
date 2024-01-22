package com.fiap.greentracefood.adapters.web.request.produto;

import com.fiap.greentracefood.application.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
@Data
@EqualsAndHashCode
@ToString
public class ProdutoRequestDTO {

    @Schema(description = "Nome do Produto", example = "Hamburguer")
    @NotNull(message = "O nome do Produto não pode ser nullo")
    private String nome;
    @Schema(description = "Descricao do Produto", example = "Pao de Hamburguer, Hamburguer, Queijo, Alface, Tomate, Molho Especial")
    private String descricao;
    @Schema(description = "Preco do Produto ", example = "19.90")
    @NotNull(message = "O preco do produto não pode ser nullo")
    private BigDecimal preco;
    @Schema(description = "Categoria do Produto", example = "LANCHE")
    @NotNull(message = "A Categoria do produto não pode ser nullo")
    private Categoria categoria;

}
