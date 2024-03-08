package com.fiap.greentracefood.domain.entity.produto.gateway;

import com.fiap.greentracefood.domain.entity.produto.enums.Categoria;
import com.fiap.greentracefood.domain.entity.produto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProdutoGateway {

    Page<Produto> consultarPorCategoria(Categoria categoria, Pageable pageable);
    Map<Long, Produto> consultarPorIds(List<Long> ids);
    Optional<Produto> consultar(Long id);
    Produto cadastrar(Produto produto);
    Produto atualizar(Long id,Produto produto);
    Boolean existsById(Long id);
    void excluir(Long id);
}
