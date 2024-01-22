package com.fiap.greentracefood.adapters.web;

import com.fiap.greentracefood.adapters.web.request.pedido.PedidoRequest;
import com.fiap.greentracefood.adapters.web.response.pedido.PedidoResponseDTO;
import com.fiap.greentracefood.adapters.web.response.pedido.PedidoResumidoResponseDTO;
import com.fiap.greentracefood.application.domain.Pedido;
import com.fiap.greentracefood.application.domain.PedidoResumido;
import com.fiap.greentracefood.application.enums.StatusPedido;
import com.fiap.greentracefood.application.port.incoming.PedidoUseCase;
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
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "pedido", description = "API responsável pelo controle de pedidos.")
public class PedidoController {
    private final ModelMapper modelMapper;
    private final PedidoUseCase pedidoUseCase;


    public PedidoController(ModelMapper modelMapper, PedidoUseCase pedidoUseCase) {
        this.modelMapper = modelMapper;
        this.pedidoUseCase = pedidoUseCase;
    }

    @Operation(summary = "Criar um pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PedidoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar o pedido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao criar o pedido",
                    content = @Content) })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO salvar(@Valid @RequestBody PedidoRequest request) {
            Pedido pedido = modelMapper.map(request, Pedido.class);
            pedido = pedidoUseCase.cadastrar(pedido);
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

    @Operation(summary = "Consultar pedido por código")
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

    @Operation(summary = "Listar todos os pedidos paginados por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao listar pedidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao listar pedidos",
                    content = @Content) })
    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoResponseDTO> listarPedidosPaginadosPorCpf(@PathVariable String cpf,
                                                                @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pedido> pedidosPage = pedidoUseCase.listarPedidosPaginadosPorCpf(cpf, pageable);
        return pedidosPage.map(p -> modelMapper.map(p, PedidoResponseDTO.class));
    }


    @Operation(summary = "Listar pedidos por CPF e data de cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PedidoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao listar pedidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao listar pedidos",
                    content = @Content) })
    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponseDTO> listarPedidosPorCpfEDataCadastro(@RequestParam String cpf,
                                                                    @RequestParam OffsetDateTime dataCadastroInicio,
                                                                    @RequestParam OffsetDateTime dataCadastroFim) {
        List<Pedido> pedidos = pedidoUseCase.listarPedidosPorCpfEDataCadastro(cpf, dataCadastroInicio, dataCadastroFim);
        return pedidos.stream()
                .map(p -> modelMapper.map(p, PedidoResponseDTO.class))
                .collect(Collectors.toList());
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
