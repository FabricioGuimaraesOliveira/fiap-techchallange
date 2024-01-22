package com.fiap.greentracefood.application.services;

import com.fiap.greentracefood.application.domain.Cliente;
import com.fiap.greentracefood.application.exceptions.ResourceNotFoundException;
import com.fiap.greentracefood.application.port.incoming.ClienteUseCase;
import com.fiap.greentracefood.application.port.outgoing.ClienteRepositoryPort;
import com.fiap.greentracefood.application.validators.CpfValidator;

public class ClienteService implements ClienteUseCase {
    final private ClienteRepositoryPort clienteRepositoryPort;
    final private CpfValidator cpfValidator;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort, CpfValidator validator) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.cpfValidator = validator;
    }

    @Override
    public Cliente buscar(String cpf) {
        return clienteRepositoryPort.buscarClientePorCPF(cpfValidator.sanitizar(cpf))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o cpf: " + cpf));
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        cliente.setCpf(cpfValidator.sanitizar(cliente.getCpf()));
        cliente.validarCadastro();
        return clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public Cliente atualizar(String cpf, Cliente request) {
        var cliente = clienteRepositoryPort.buscarClientePorCPF(cpfValidator.sanitizar(cpf))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o cpf: " + cpf));
        cliente.atualizar(request.getNome(), request.getEmail());
        return clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public void excluir(String email) {

    }
}
