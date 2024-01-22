package com.fiap.greentracefood.adapters.persistence.repository.produto;

import com.fiap.greentracefood.adapters.persistence.entity.ProdutoEntity;
import com.fiap.greentracefood.application.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, Long>, JpaSpecificationExecutor<ProdutoEntity> {
    @Query("SELECT p FROM ProdutoEntity p WHERE p.categoria = :categoria")
    Page<ProdutoEntity> findByCategoria(Categoria categoria, Pageable pageable);

}