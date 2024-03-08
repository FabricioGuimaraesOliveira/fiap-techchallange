package com.fiap.greentracefood.infrastructure.persistence.produto;//package com.fiap.greentracefood.adapters.persistence.entity;

import com.fiap.greentracefood.domain.entity.produto.enums.Categoria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "produtos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoEntity {
    @Id
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}