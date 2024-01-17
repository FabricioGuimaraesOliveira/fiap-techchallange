package com.fiap.greentracefood.application.services;

import com.fiap.greentracefood.application.domain.Cliente;
import com.fiap.greentracefood.application.domain.Pedido;
import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.enums.StatusPedido;
import com.fiap.greentracefood.application.port.incoming.PedidoUseCase;
import com.fiap.greentracefood.application.port.outgoing.PedidoRepositoryPort;

import java.math.BigDecimal;
import java.util.List;

public class PedidoService implements PedidoUseCase {
    final private PedidoRepositoryPort pedidoRepository;
    final private ProdutoService produtoService;
    final private ClienteService clienteService;

    public PedidoService(PedidoRepositoryPort pedidoRepository, ProdutoService produtoService, ClienteService clienteService) {
        this.clienteService = clienteService;
        this.produtoService= produtoService;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> consultaByStatus(StatusPedido status) {
        return null;
    }

    @Override
    public Pedido cadastrar(Pedido pedido) {

        validarPedido(pedido);
        validarItens(pedido);
        pedido.setTaxaFrete(new BigDecimal(1));
        pedido.calcularValorTotal();

        return pedidoRepository.salvar(pedido);
    }
    @Override
    public void cancelar(Long id) {
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        if(pedido.podeSerCancelado()){
            pedido.cancelar();
            pedidoRepository.salvar(pedido);
        }
    }
    @Override
    public void preparar(Long id) {
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        if(pedido.podeSerConfirmado()){
            pedido.confirmar();
            pedidoRepository.salvar(pedido);
        }
    }

    @Override
    public void entregar(Long id) {
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        if(pedido.podeSerEntregue()){
            pedido.entregar();
            pedidoRepository.salvar(pedido);
        }
    }

    @Override
    public void finalizar(Long id) {
    }

    private void validarPedido(Pedido pedido) {
        Cliente cliente = clienteService.buscarClientePorId(pedido.getCliente().getId());
        pedido.setCliente(cliente);
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = produtoService.buscarProdutoPorId(item.getProduto().getId());
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }
}
