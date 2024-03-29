package com.fiap.greentracefood.application.domain;

import com.fiap.greentracefood.application.enums.StatusPagamento;

public class Pagamento {
    private long id;
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