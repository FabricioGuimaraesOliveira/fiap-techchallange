package com.fiap.greentracefood.infrastructure.pedido.controller;



import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.domain.entity.pedido.model.PedidoResumido;
import com.fiap.greentracefood.infrastructure.authorization.JwtDecoder;
import com.fiap.greentracefood.infrastructure.pedido.dto.request.PedidoRequest;
import com.fiap.greentracefood.infrastructure.pedido.dto.response.PedidoResponseDTO;
import com.fiap.greentracefood.infrastructure.pedido.dto.response.PedidoResumidoResponseDTO;
import com.fiap.greentracefood.usecases.pedido.PedidoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/pedidos")
@Tag(name = "pedido", description = "API responsável pelo controle de pedidos.")
public class PedidoController {
    private final ModelMapper modelMapper;
    private final PedidoUseCase pedidoUseCase;
    private final JwtDecoder jwtDecoder;


    public PedidoController(ModelMapper modelMapper, PedidoUseCase pedidoUseCase, JwtDecoder jwtDecoder) {
        this.modelMapper = modelMapper;
        this.pedidoUseCase = pedidoUseCase;
        this.jwtDecoder = jwtDecoder;
    }

    @Operation(summary = "Realizar Checkout do Pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Checkout do Pedido realizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PedidoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar o pedido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao criar o pedido",
                    content = @Content) })
    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
//    public PedidoResponseDTO salvar(@Valid @RequestBody PedidoRequest request,
//                                     @RequestHeader("Authorization") String authorization) {
//            Pedido pedido = modelMapper.map(request, Pedido.class);
//            var cpf=jwtDecoder.decodeAndExtractCPF(authorization);
//            pedido = pedidoUseCase.cadastrar(pedido,cpf);
//            return modelMapper.map(pedido, PedidoResponseDTO.class);
//    }
    public PedidoResponseDTO salvar(@Valid @RequestBody PedidoRequest request) {
		Pedido pedido = modelMapper.map(request, Pedido.class);
		var cpf = request.getCliente().getCpf();
		pedido = pedidoUseCase.cadastrar(pedido,cpf);
		return modelMapper.map(pedido, PedidoResponseDTO.class);
    }

    @Operation(summary = "Alterar o status do pedido")
    @ApiResponse(responseCode = "200", description = "Status do pedido alterado com sucesso",
            content = { @Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Erro ao alterar o status do pedido",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
            content = @Content)
    @PatchMapping("/{codigo}/alterar-status")
    @ResponseStatus(HttpStatus.OK)
    void alterarStatusPedido(
            @Parameter(description = "Código do pedido", required = true)
            @PathVariable String codigo,
            @Parameter(description = "Atualizar Status do Pedido", required = true)
            @RequestParam StatusPedido status) {
             pedidoUseCase.alterarStatusPedido(codigo, status);
    }

    @Operation(summary = "Consultar resumo do pedido por código")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PedidoResumidoResponseDTO.class)) })
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @GetMapping("/{codigo}/consultar")
    public ResponseEntity<PedidoResumidoResponseDTO> consultarPorCodigo(@PathVariable String codigo) {
       Optional<PedidoResumido> pedidoOptional = pedidoUseCase.consultarPorCodigo(codigo);
        return pedidoOptional
                .map(pedido -> modelMapper.map(pedido, PedidoResumidoResponseDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Detalhar pedido por código")
    @ApiResponse(responseCode = "200", description = "Pedido detalhado encontrado",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PedidoResponseDTO.class)) })
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @GetMapping("/{codigo}/detalhar")
    public ResponseEntity<PedidoResponseDTO> detalharPorCodigo(@PathVariable String codigo) {
        Optional<Pedido> pedidoDetalhadoOptional = pedidoUseCase.detalharPorCodigo(codigo);
        return pedidoDetalhadoOptional
                .map(pedidoDetalhado -> modelMapper.map(pedidoDetalhado, PedidoResponseDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Listar todos os pedidos paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Erro ao listar pedidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao listar pedidos",
                    content = @Content) })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoResponseDTO> listarPedidosPaginados(
            @Parameter(description = "CPF do cliente (opcional)")
            @RequestParam(required = false) String cpf,
            @Parameter(description = "Número da página (padrão: 0)")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Número de itens por página (padrão: 10)")
            @RequestParam(defaultValue = "10") int size) {


        Pageable pageable = PageRequest.of(page, size);
        Page<Pedido> pedidosPage;

        if (cpf != null) {
            pedidosPage = pedidoUseCase.listarPedidosPaginadosPorCpf(cpf, pageable);
        } else {
            pedidosPage = pedidoUseCase.listarTodosPedidosPaginados(pageable);
        }

        Page<PedidoResponseDTO> pedidoResponseDTOPage = pedidosPage.map(
                pedido -> modelMapper.map(pedido, PedidoResponseDTO.class)
        );
        return pedidoResponseDTOPage;
    }

    @Operation(summary = "Listar todos os pedidos paginados por status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PedidoResumidoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao listar pedidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao listar pedidos",
                    content = @Content) })
    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoResumidoResponseDTO> listarPedidosPorStatusPaginados(
            @PathVariable StatusPedido status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<PedidoResumido> pedidosPage = pedidoUseCase.listarPedidosPorStatus(status, pageable);

        return pedidosPage.map(p -> modelMapper.map(p, PedidoResumidoResponseDTO.class));
    }
}
