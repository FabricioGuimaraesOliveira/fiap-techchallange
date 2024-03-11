package com.fiap.greentracefood.infrastructure.persistence.pagamento;


import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.infrastructure.persistence.pedido.PedidoEntity;
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
@Table(name = "pagamentos")
public class PagamentoEntity {
    @Id
    @SequenceGenerator(name = "pagamento_seq", sequenceName = "pagamento_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamento_seq")
    private long id;
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    @OneToOne(mappedBy = "pagamento", fetch = FetchType.LAZY)
    private PedidoEntity pedido;
    private String qrCodeData;

}