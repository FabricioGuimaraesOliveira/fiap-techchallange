package com.fiap.greentracefood.usecases.cliente;

import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteCpfGateway;
import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteGateway;
import com.fiap.greentracefood.domain.entity.cliente.model.Cliente;
import com.fiap.greentracefood.domain.entity.cliente.validator.CpfValidator;
import com.fiap.greentracefood.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class ClienteUseCase {

    final private ClienteGateway clienteGateway;
final private ClienteCpfGateway clienteCpfGateway;

    public ClienteUseCase(ClienteGateway clienteGateway, ClienteCpfGateway clienteCpfGateway) {
        this.clienteGateway = clienteGateway;
        this.clienteCpfGateway = clienteCpfGateway;
    }


    public Cliente buscar(String cpf) {
        return clienteGateway.buscarClientePorCPF(CpfValidator.sanitizar(cpf))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o cpf: " + cpf));
    }

    public Cliente cadastrar(Cliente cliente) {
        cliente.setCpf(CpfValidator.sanitizar(cliente.getCpf()));
        cliente.validarCadastro();
        var clientEntity = clienteGateway.salvar(cliente);
        clienteCpfGateway.save(clientEntity.getCpf());
        return clientEntity;
    }

    public Cliente atualizar(String cpf, Cliente request) {
        var cliente = clienteGateway.buscarClientePorCPF(CpfValidator.sanitizar(cpf))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o cpf: " + cpf));
        cliente.atualizar(request.getNome(), request.getEmail());
        return clienteGateway.salvar(cliente);
    }

    public Page<Cliente> listarPaginado(Pageable pageable) {
        return clienteGateway.listarPaginado(pageable);
    }

}
