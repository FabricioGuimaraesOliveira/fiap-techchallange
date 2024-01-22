package com.fiap.greentracefood.adapters.web.response.pagamento;

import com.fiap.greentracefood.application.enums.StatusPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoResponseDTO {
    @Schema(description = "Codigo do Pagamento", example = "2345677")
    private Long id;
    @Schema(description = "Status do pagamento", example = "PAGO|NAO_PAGO")
    private StatusPagamento statusPagamento;
}
