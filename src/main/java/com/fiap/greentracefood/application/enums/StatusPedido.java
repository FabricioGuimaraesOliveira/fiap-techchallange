package com.fiap.greentracefood.application.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO("CRIADO"),
    PREPARANDO("PREPARANDO", CRIADO),
    ENTREGUE("ENTREGUE", PREPARANDO),
    CANCELADO("CANCELADO", CRIADO, PREPARANDO),
    FINALIZADO("FINALIZADO", PREPARANDO,ENTREGUE);

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