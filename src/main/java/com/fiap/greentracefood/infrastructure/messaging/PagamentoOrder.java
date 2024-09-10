package com.fiap.greentracefood.infrastructure.messaging;


import java.math.BigDecimal;

public class PagamentoOrder {
    private String codigo;
    private BigDecimal valorTotal;

    // Construtor padrão
    public PagamentoOrder() {
    }

    // Construtor com parâmetros
    public PagamentoOrder(String codigo, BigDecimal valorTotal) {
        this.codigo = codigo;
        this.valorTotal = valorTotal;
    }

    // Getters e setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "PagamentoOrder{" +
                "codigo='" + codigo + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
