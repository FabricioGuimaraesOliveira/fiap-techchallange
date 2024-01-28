package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


public interface ClienteUseCase {
    Cliente buscar(String cpf);
    Cliente cadastrar(Cliente dto);
    Cliente atualizar(String cpf,Cliente cliente);
    Page<Cliente> listarPaginado(Pageable pageable);


}
