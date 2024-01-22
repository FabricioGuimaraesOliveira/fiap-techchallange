package com.fiap.greentracefood.application.exceptions;

public class ProdutosInvalidosException extends RuntimeException {

    public ProdutosInvalidosException() {
        super("Não foi possível prosseguir com o pedido. Existem produtos inválidos.");
    }

}