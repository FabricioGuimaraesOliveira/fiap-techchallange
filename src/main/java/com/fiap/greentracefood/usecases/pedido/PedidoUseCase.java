package com.fiap.greentracefood.usecases.pedido;



import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteGateway;
import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.domain.entity.pedido.model.PedidoResumido;
import com.fiap.greentracefood.domain.entity.produto.gateway.ProdutoGateway;
import com.fiap.greentracefood.domain.entity.produto.model.Produto;
import com.fiap.greentracefood.infrastructure.exceptions.ProdutosInvalidosException;
import com.fiap.greentracefood.infrastructure.exceptions.StatusPedidoException;
import com.fiap.greentracefood.infrastructure.messaging.PaymentSender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


public class PedidoUseCase {
    private final PedidoGateway pedidoGateway;
    private final ProdutoGateway produtoGateway;
    private final ClienteGateway clienteGateway;
    private final PaymentSender paymentSender;

    public PedidoUseCase(PedidoGateway pedidoGateway, ProdutoGateway produtoGateway, ClienteGateway clienteGateway, PaymentSender paymentSender) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.clienteGateway = clienteGateway;
        this.paymentSender = paymentSender;
    }


    public Optional<PedidoResumido> consultarPorCodigo(String codigoPedido) {
        return pedidoGateway.consultarPorCodigo(codigoPedido);
    }


    public Pedido cadastrar(Pedido pedido,String cpf) {
        validarPedido(pedido,cpf);
        validarItens(pedido);
        pedido.setTaxaFrete(new BigDecimal(1));
        pedido.inicializarPagamento();
        pedido.calcularValorTotal();
        paymentSender.sendProcessedPaymentMessage(pedido);

        return pedidoGateway.salvar(pedido);
    }

    public Page<Pedido> listarPedidosPaginadosPorCpf(String cpf, Pageable pageable) {
        return pedidoGateway.listarPedidosPaginadosPorCpf(cpf, pageable);
    }


    public void alterarStatusPedido(String codigo, StatusPedido status) {
        Pedido pedido = pedidoGateway.detalharPorCodigo(codigo).orElseThrow();

        validarTransicaoStatus(pedido, status);
        pedido.setStatus(status);
        pedidoGateway.salvar(pedido);

    }

    public Page<PedidoResumido> listarPedidosPorStatus(StatusPedido status, Pageable pageable) {
        return pedidoGateway.listarPedidosPorStatus(status, pageable);
    }

    public Page<Pedido> listarTodosPedidosPaginados(Pageable pageable) {
        return pedidoGateway.listarTodosPedidosPaginados(pageable);

    }

    public Optional<Pedido> detalharPorCodigo(String codigoPedido) {
        return pedidoGateway.detalharPorCodigo(codigoPedido);
    }

    private void validarTransicaoStatus(Pedido pedido, StatusPedido novoStatus) {
        switch (novoStatus) {
            case PREPARANDO:
                if (pedido.podeSerPreparado()) {
                    pedido.preparar();
                } else {
                    throw new StatusPedidoException(novoStatus, pedido.isPago());
                }
                break;
            case PRONTO:
                if (pedido.podeSerEntregue()) {
                    pedido.entregar();
                } else {
                    throw new StatusPedidoException(novoStatus, pedido.isPago());
                }
                break;
            case CANCELADO:
                if (pedido.podeSerCancelado()) {
                    pedido.cancelar();
                } else {
                    throw new StatusPedidoException(novoStatus, pedido.isPago());
                }
                break;
            case FINALIZADO:
                if (pedido.podeSerFinalizado()) {
                    pedido.finalizar();
                } else {
                    throw new StatusPedidoException(novoStatus, pedido.isPago());
                }
                break;
            default:
                throw new StatusPedidoException(novoStatus, pedido.isPago());
        }
    }


    private void validarPedido(Pedido pedido,String cpf) {
        if (Objects.nonNull(cpf)) {
            pedido.setCliente(clienteGateway.buscarClientePorCPF(cpf).orElse(null));
        }
    }

    private void validarItens(Pedido pedido) {
        List<Long> produtoIds = pedido.getItens().stream()
                .map(item -> item.getProduto().getId())
                .toList();

        Map<Long, Produto> produtos = produtoGateway.consultarPorIds(produtoIds);

        if (!saoTodosProdutosValidos(produtos, pedido)) {
            throw new ProdutosInvalidosException();
        }
        pedido.getItens().forEach(item -> {
            Produto produto = produtos.get(item.getProduto().getId());
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    private boolean saoTodosProdutosValidos(Map<Long, Produto> produtos, Pedido pedido) {
        List<Long> produtoIdsNoPedido = pedido.getItens().stream()
                .map(item -> item.getProduto().getId())
                .toList();

        return produtos.keySet().containsAll(produtoIdsNoPedido);
    }
}
