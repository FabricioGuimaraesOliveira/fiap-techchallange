package com.fiap.greentracefood.adapters.web;

import com.fiap.greentracefood.adapters.web.request.cliente.ClienteRequestDTO;
import com.fiap.greentracefood.adapters.web.request.cliente.ClienteRequestUpdateDTO;
import com.fiap.greentracefood.adapters.web.response.cliente.ClienteResponseDTO;
import com.fiap.greentracefood.application.domain.Cliente;
import com.fiap.greentracefood.application.port.incoming.ClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "cliente", description = "API responsável pelo cadastrado de clientes")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;
    private  final ModelMapper modelMapper;

    public ClienteController(ClienteUseCase clienteUseCase, ModelMapper modelMapper) {
        this.clienteUseCase = clienteUseCase;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Buscar um Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar o Cliente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar o Cliente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar o Cliente",
                    content = @Content) })
    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarCliente(@Schema(description = "Cpf do Cliente") @PathVariable(value = "cpf") @CPF(message = "Documento Invalido") String  cpf) {
        return modelMapper.map(clienteUseCase.buscar(cpf), ClienteResponseDTO.class);

    }

    @Operation(summary = "Cadastrar um Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar o Cliente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o Cliente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao cadastrar o Cliente",
                    content = @Content) })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastrarCliente(@Valid @RequestBody ClienteRequestDTO request) {
        Cliente cliente = modelMapper.map(request, Cliente.class);
        return modelMapper.map(clienteUseCase.cadastrar(cliente), ClienteResponseDTO.class);

    }

    @Operation(summary = "Atualizar as informacoes do Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o Cliente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o Cliente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Erro ao atualizar o Cliente",
                    content = @Content) })
    @PutMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO atualizarCliente(@PathVariable @Valid @CPF(message = "CPF do Cliente inválido") String cpf, @Valid @RequestBody ClienteRequestUpdateDTO request) {
        Cliente cliente = modelMapper.map(request, Cliente.class);
        return modelMapper.map(clienteUseCase.atualizar(cpf,cliente), ClienteResponseDTO.class);

    }

}
