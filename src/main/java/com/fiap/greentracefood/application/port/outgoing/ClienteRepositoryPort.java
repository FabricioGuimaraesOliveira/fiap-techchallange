package com.fiap.greentracefood.application.port.outgoing;

import com.fiap.greentracefood.application.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarClientePorCPF(String cpf);
    Page<Cliente> findAll(Pageable pageable);
}
