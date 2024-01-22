package com.fiap.greentracefood.adapters.web.request.pedido;

import com.fiap.greentracefood.adapters.web.request.cliente.ClientePedidoRequestDTO;
import com.fiap.greentracefood.adapters.web.request.item.ItemPedidoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    @Valid
    @NotNull
    ClientePedidoRequestDTO  cliente;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoRequest> itens;
}