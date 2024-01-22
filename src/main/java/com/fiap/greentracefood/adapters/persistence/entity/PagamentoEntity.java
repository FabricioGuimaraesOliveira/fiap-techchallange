package com.fiap.greentracefood.adapters.persistence.entity;


import com.fiap.greentracefood.application.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    @OneToOne(mappedBy = "pagamento", fetch = FetchType.LAZY)
    private PedidoEntity pedido;

}