package com.fiap.greentracefood.application.services;

import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.enums.Categoria;
import com.fiap.greentracefood.application.exceptions.ResourceNotFoundException;
import com.fiap.greentracefood.application.port.incoming.ProdutoUseCase;
import com.fiap.greentracefood.application.port.outgoing.ProdutoRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProdutoService implements ProdutoUseCase {
    final private ProdutoRepositoryPort produtoRepositoryPort;
    public ProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public Page<Produto> consultaByCategoria(Categoria categoria, Pageable pageable) {
       return produtoRepositoryPort.consultarPorCategoria(categoria, pageable);
    }
    @Override
    public Map<Long, Produto> consultarPorIds(List<Long> ids) {
        return produtoRepositoryPort.consultarPorIds(ids);
    }

    @Override
    public Produto consultar(Long id) {
       return produtoRepositoryPort.consultar(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }

    @Override
    public Produto cadastrar(Produto produto) {
        validarCamposProduto(produto);
        return produtoRepositoryPort.cadastrar(produto);
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {

        Produto existingProduto = produtoRepositoryPort.consultar(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para o ID: " + id));

        validarCamposProduto(produto);

        existingProduto.setNome(produto.getNome());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setCategoria(produto.getCategoria());
        return produtoRepositoryPort.atualizar(id, existingProduto);
    }
    @Override
    public void excluir(Long id) {

        if (!produtoRepositoryPort.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com o ID: " + id);
        }
        produtoRepositoryPort.excluir(id);

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
