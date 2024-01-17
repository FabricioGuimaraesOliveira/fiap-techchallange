package com.fiap.greentracefood.application.domain;

import com.fiap.greentracefood.application.enums.StatusPagamento;

public class Pagamento {
    private long id;
    private StatusPagamento pago;
    private String idGatewayPagamento;
    private String ticketUrl;

}
