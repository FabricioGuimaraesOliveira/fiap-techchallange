package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProdutoUseCase {

    Page<Produto> consultaByCategoria(Categoria categoria, Pageable pageable);
    Map<Long, Produto> consultarPorIds(List<Long> ids);
    Produto consultar(Long id);
    Produto cadastrar(Produto produto);
    Produto atualizar(Long id,Produto produto);
    void excluir(Long id);
}
