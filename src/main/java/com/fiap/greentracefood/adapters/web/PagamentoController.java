package com.fiap.greentracefood.adapters.web;
import com.fiap.greentracefood.adapters.web.response.pagamento.PagamentoResponseDTO;
import com.fiap.greentracefood.application.domain.Pagamento;
import com.fiap.greentracefood.application.enums.StatusPagamento;
import com.fiap.greentracefood.application.port.incoming.PagamentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "pagamento", description = "API responsável pelo gerenciamento de pagamentos.")
public class PagamentoController {

    private final ModelMapper modelMapper;
    private final PagamentoUseCase pagamentoUseCase;

    public PagamentoController(ModelMapper modelMapper, PagamentoUseCase pagamentoUseCase) {
        this.modelMapper = modelMapper;
        this.pagamentoUseCase = pagamentoUseCase;
    }

    @Operation(summary = "Consultar pagamento por código do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagamentoResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado",
                    content = @Content) })
    @GetMapping("/{codigoPedido}")
    public PagamentoResponseDTO consultarPagamentoPorPedido(@PathVariable String codigoPedido) {
        Pagamento pagamento = pagamentoUseCase.consultarPorPedido(codigoPedido);
        return modelMapper.map(pagamento, PagamentoResponseDTO.class);
    }

    @Operation(summary = "Fake Checkout - Endpoint utilizado para registrar o pagamento do pedido solicitado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento registrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagamentoResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content) })
    @PostMapping("webhook/{codigoPedido}/checkout")
    @ResponseStatus(HttpStatus.OK)
   void registrarPagamentoPorPedido(
            @PathVariable String codigoPedido,
            @RequestParam(name = "statusPagamento") StatusPagamento statusPagamento) {
        pagamentoUseCase.registrarPagamento(codigoPedido, statusPagamento);

    }
}