package com.fiap.greentracefood.domain.entity.pagamento.model;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;


public class Pagamento {
    private long id;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    private String qrCode;
    private StatusPagamento status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    private Pedido pedido;

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }


    private Pagamento() {
    }

    public static PagamentoBuilder builder() {
        return new PagamentoBuilder();
    }

    public static class PagamentoBuilder {
        private Pagamento instancia = new Pagamento();

        private PagamentoBuilder() {
        }

        public PagamentoBuilder id(long id) {
            instancia.id = id;
            return this;
        }

        public PagamentoBuilder pago(StatusPagamento pago) {
            instancia.status = pago;
            return this;
        }

        public Pagamento build() {
            return instancia;
        }
    }
}