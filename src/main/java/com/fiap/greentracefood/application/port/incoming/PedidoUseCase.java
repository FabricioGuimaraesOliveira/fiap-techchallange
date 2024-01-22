package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Pedido;
import com.fiap.greentracefood.application.domain.PedidoResumido;
import com.fiap.greentracefood.application.enums.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoUseCase {
    List<Pedido> consultaByStatus(StatusPedido status);

    Optional<PedidoResumido> consultarPorCodigo(String codigoPedido);
    Pedido cadastrar(Pedido dto);
    List<Pedido>  listarPedidosPorCpfEDataCadastro(String cpf, OffsetDateTime dataCadastroInicio, OffsetDateTime dataCadastroFim);
    Page<Pedido> listarPedidosPaginadosPorCpf(String cpf, Pageable pageable);
    void alterarStatusPedido(String codigo, StatusPedido status);
    Page<PedidoResumido> listarPedidosPorStatus(StatusPedido status, Pageable pageable);



}
