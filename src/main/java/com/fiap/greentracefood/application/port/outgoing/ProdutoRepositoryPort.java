package com.fiap.greentracefood.application.port.outgoing;

import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProdutoRepositoryPort {
    Produto cadastrar(Produto produto);
    Optional<Produto> consultar(Long id);
    Page<Produto> consultarPorCategoria(Categoria categoria, Pageable pageable);
    Map<Long, Produto> consultarPorIds(List<Long> ids);
    Produto atualizar(Long id,Produto produto);
    void excluir(Long id);
    boolean existsById(Long id);

}
