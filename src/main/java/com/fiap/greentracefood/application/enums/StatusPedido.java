package com.fiap.greentracefood.application.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    RECEBIDO("RECEBIDO"),
    PREPARANDO("PREPARANDO", RECEBIDO),
    PRONTO("PRONTO", PREPARANDO),
    CANCELADO("CANCELADO", RECEBIDO, PREPARANDO),
    FINALIZADO("FINALIZADO", PREPARANDO, PRONTO);

    private String descricao;
    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao,  StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public  boolean naoPodeAlterarPara(StatusPedido novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }

    public  boolean podeAlterarPara(StatusPedido novoStatus) {
        return !naoPodeAlterarPara(novoStatus);
    }

    public String getDescricao() {
        return this.descricao;
    }
}