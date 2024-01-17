package com.fiap.greentracefood.adapters.persistence.entity;


import com.fiap.greentracefood.application.enums.StatusPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pagamento")
@Table(name = "PAGAMENTOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento pago;

    @Size(max=400)
    private String idGatewayPagamento;

    @Size(max=400)
    private String ticketUrl;

}