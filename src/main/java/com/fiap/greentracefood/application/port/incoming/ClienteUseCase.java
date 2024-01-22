package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Cliente;

public interface ClienteUseCase {
    Cliente buscar(String cpf);

    Cliente cadastrar(Cliente dto);
    Cliente atualizar(String cpf,Cliente cliente);
    void excluir(String email);
}
