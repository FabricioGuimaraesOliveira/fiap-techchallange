package com.fiap.greentracefood.adapters.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.greentracefood.application.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Pedidos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
public class PedidoEntity extends AbstractAggregateRoot<PedidoEntity> {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status = StatusPedido.CRIADO;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataFinalizacao;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("pedido")
    private List<ItemPedidoEntity> itens = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id", nullable = false)
    private PagamentoEntity pagamento;

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }


}