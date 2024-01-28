package com.fiap.greentracefood.application.services;

import com.fiap.greentracefood.application.domain.Pedido;
import com.fiap.greentracefood.application.domain.PedidoResumido;
import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.enums.StatusPedido;
import com.fiap.greentracefood.application.exceptions.ProdutosInvalidosException;
import com.fiap.greentracefood.application.exceptions.StatusPedidoException;
import com.fiap.greentracefood.application.port.incoming.ClienteUseCase;
import com.fiap.greentracefood.application.port.incoming.PedidoUseCase;
import com.fiap.greentracefood.application.port.incoming.ProdutoUseCase;
import com.fiap.greentracefood.application.port.outgoing.PedidoRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoService implements PedidoUseCase {

    public PedidoService(PedidoRepositoryPort pedidoRepository, ProdutoUseCase produtoService, ClienteUseCase clienteService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    final private PedidoRepositoryPort pedidoRepository;
    final private ProdutoUseCase produtoService;
    final private ClienteUseCase clienteService;


    @Override
    public Optional<PedidoResumido> consultarPorCodigo(String codigoPedido) {
        return pedidoRepository.buscarResumoPedidoPorCodigo(codigoPedido);
    }

    @Override
    public Pedido cadastrar(Pedido pedido) {

        validarPedido(pedido);
        validarItens(pedido);
        pedido.setTaxaFrete(new BigDecimal(1));
        pedido.inicializarPagamento();
        pedido.calcularValorTotal();

        return pedidoRepository.salvar(pedido);
    }

    @Override
    public Page<Pedido> listarPedidosPaginadosPorCpf(String cpf, Pageable pageable) {
        return pedidoRepository.listarPedidosPaginadosPorCpf(cpf, pageable);
    }

    @Override
    public void alterarStatusPedido(String codigo, StatusPedido status) {
        Pedido pedido = pedidoRepository.buscarPedidoPorCodigo(codigo).orElseThrow();

        validarTransicaoStatus(pedido, status);
        pedido.setStatus(status);
        pedidoRepository.salvar(pedido);

    }

    @Override
    public Page<PedidoResumido> listarPedidosPorStatus(StatusPedido status, Pageable pageable) {
        return pedidoRepository.listarPedidosPorStatus(status, pageable);
    }

    @Override
    public Page<Pedido> listarTodosPedidosPaginados(Pageable pageable) {
        return pedidoRepository.listarTodosPedidosPaginados(pageable);

    }

    @Override
    public Optional<Pedido> detalharPorCodigo(String codigoPedido) {
        return pedidoRepository.buscarPedidoPorCodigo(codigoPedido);
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
            case ENTREGUE:
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


    private void validarPedido(Pedido pedido) {
        if (Objects.nonNull(pedido.getCliente())) {
            pedido.setCliente(clienteService.buscar(pedido.getCliente().getCpf()));
        }
    }

    private void validarItens(Pedido pedido) {
        List<Long> produtoIds = pedido.getItens().stream()
                .map(item -> item.getProduto().getId())
                .collect(Collectors.toList());

        Map<Long, Produto> produtos = produtoService.consultarPorIds(produtoIds);

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
                .collect(Collectors.toList());

        return produtos.keySet().containsAll(produtoIdsNoPedido);
    }
}
