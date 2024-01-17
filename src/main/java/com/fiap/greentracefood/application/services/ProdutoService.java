package com.fiap.greentracefood.application.services;

import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.port.outgoing.ProdutoRepositoryPort;

public class ProdutoService {
    final private ProdutoRepositoryPort produtoRepositoryPort;
    public ProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    public Produto  buscarProdutoPorId(Long id) {
        return new Produto();
    }
}
