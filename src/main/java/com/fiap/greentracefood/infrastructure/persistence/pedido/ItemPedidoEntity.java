package com.fiap.greentracefood.infrastructure.persistence.pedido;//package com.fiap.greentracefood.adapters.persistence.entity;


import com.fiap.greentracefood.infrastructure.persistence.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "pedido_itens")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedidoEntity {

    @Id
    @EqualsAndHashCode.Include
    @SequenceGenerator(name = "pedido_item_seq", sequenceName = "pedido_item_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_item_seq")
    private Long id;

    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private Integer quantidade;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

}