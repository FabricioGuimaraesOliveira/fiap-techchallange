package com.fiap.greentracefood.usecases.pagamento;


import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.infrastructure.exceptions.ResourceNotFoundException;
import com.fiap.greentracefood.infrastructure.mercadopago.gateway.MercadoPagoGateway;

public class PagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;
    private final PedidoGateway pedidoGateway;
    private final MercadoPagoGateway mercadoPagoGateway;

    public PagamentoUseCase(PagamentoGateway pagamentoGateway, PedidoGateway pedidoGateway, MercadoPagoGateway mercadoPagoGateway) {
        this.pagamentoGateway = pagamentoGateway;
        this.pedidoGateway = pedidoGateway;
        this.mercadoPagoGateway = mercadoPagoGateway;
    }

    public Pagamento consultarPorPedido(String codigoPedido) {
        return pagamentoGateway.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public String iniciarPagamento(String codigoPedido) {
        Pedido pedido = buscarPedidoPorCodigo(codigoPedido);
        String qrCode = gerarQRCode(pedido);
        atualizarStatusPagamento(pedido);
        return qrCode;
    }

    private Pedido buscarPedidoPorCodigo(String codigoPedido) {
        return pedidoGateway.detalharPorCodigo(codigoPedido)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
    }

    private String gerarQRCode(Pedido pedido) {
        return mercadoPagoGateway.generateQRCodeMock(pedido.getId(), pedido.getValorTotal());
    }

    private void atualizarStatusPagamento(Pedido pedido) {
        Pagamento pagamento = pedido.getPagamento();
        pagamento.setStatus(StatusPagamento.PAGAMENTO_INICIADO);
        pagamentoGateway.salvar(pagamento);
    }
    public void registrarPagamento(String codigoPedido, StatusPagamento statusPagamento) {
        if (statusPagamento != StatusPagamento.REJEITADO && statusPagamento != StatusPagamento.APROVADO) {
            throw new IllegalArgumentException("O status do pagamento deve ser REJEITADO ou APROVADO");
        }
        Pagamento pagamento = pagamentoGateway.consultarPorPedido(codigoPedido).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        pagamento.setStatus(statusPagamento);
        pagamentoGateway.salvar(pagamento);
    }
}
