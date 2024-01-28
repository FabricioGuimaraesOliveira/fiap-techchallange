package com.fiap.greentracefood.adapters.web.request.pedido;

import com.fiap.greentracefood.adapters.web.request.cliente.ClientePedidoRequestDTO;
import com.fiap.greentracefood.adapters.web.request.item.ItemPedidoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

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