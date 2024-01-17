package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Pedido;
import com.fiap.greentracefood.application.enums.StatusPedido;

import java.util.List;

public interface PedidoUseCase {
    public List<Pedido> consultaByStatus(StatusPedido status);
    public Pedido cadastrar(Pedido dto);
    public void cancelar(Long id);
    public void preparar(Long id);
    public void entregar(Long id);
    public void finalizar(Long id);
}
