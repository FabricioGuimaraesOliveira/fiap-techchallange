package com.fiap.greentracefood.infrastructure.pedido.dto.request;


import com.fiap.greentracefood.infrastructure.cliente.dto.request.ClientePedidoRequestDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    @Schema(description = "Cliente associado ao pedido. Pode ser nulo se o cliente não estiver disponível.")
    ClientePedidoRequestDTO cliente;

    @Valid
    @NotNull
    @Size(min = 1)
    @ArraySchema(minItems = 1, schema = @Schema(description = "Itens do pedido. Deve conter pelo menos um item."))
    private List<ItemPedidoRequest> itens;
}