package com.fiap.greentracefood.infrastructure.persistence.produto;

import com.fiap.greentracefood.domain.entity.produto.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, Long>, JpaSpecificationExecutor<ProdutoEntity> {
    @Query("SELECT p FROM ProdutoEntity p WHERE p.categoria = :categoria")
    Page<ProdutoEntity> findByCategoria(Categoria categoria, Pageable pageable);

}