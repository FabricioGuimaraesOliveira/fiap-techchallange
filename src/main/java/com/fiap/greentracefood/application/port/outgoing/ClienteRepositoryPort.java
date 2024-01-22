package com.fiap.greentracefood.application.port.outgoing;

import com.fiap.greentracefood.application.domain.Cliente;

import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarClientePorCPF(String cpf);
}
