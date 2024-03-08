package com.fiap.greentracefood.usecases.produto;


import com.fiap.greentracefood.domain.entity.produto.enums.Categoria;
import com.fiap.greentracefood.domain.entity.produto.gateway.ProdutoGateway;
import com.fiap.greentracefood.domain.entity.produto.model.Produto;
import com.fiap.greentracefood.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProdutoUseCase {
    final private ProdutoGateway produtoGateway;

    public ProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }


    public Page<Produto> consultaByCategoria(Categoria categoria, Pageable pageable) {
        return produtoGateway.consultarPorCategoria(categoria, pageable);
    }

    public Map<Long, Produto> consultarPorIds(List<Long> ids) {
        return produtoGateway.consultarPorIds(ids);
    }


    public Produto consultar(Long id) {
        return produtoGateway.consultar(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }

    public Produto cadastrar(Produto produto) {
        validarCamposProduto(produto);
        return produtoGateway.cadastrar(produto);
    }

    public Produto atualizar(Long id, Produto produto) {

        Produto existingProduto = produtoGateway.consultar(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para o ID: " + id));

        validarCamposProduto(produto);

        existingProduto.setNome(produto.getNome());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setCategoria(produto.getCategoria());
        return produtoGateway.atualizar(id, existingProduto);
    }

    public void excluir(Long id) {

        if (!produtoGateway.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com o ID: " + id);
        }
        produtoGateway.excluir(id);

    }

    private void validarCamposProduto(Produto produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero.");
        }
        if (produto.getCategoria() == null || !Categoria.contains(produto.getCategoria())) {
            throw new IllegalArgumentException("Categoria do produto é inválida.");
        }
    }
}
