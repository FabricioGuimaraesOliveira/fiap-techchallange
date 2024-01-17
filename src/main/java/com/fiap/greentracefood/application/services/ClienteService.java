package com.fiap.greentracefood.application.services;

import com.fiap.greentracefood.application.domain.Cliente;
import com.fiap.greentracefood.application.port.outgoing.ClienteRepositoryPort;

public class ClienteService {
    final private ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    public Cliente buscarClientePorId(Long id) {
            return new Cliente();
        }
}
