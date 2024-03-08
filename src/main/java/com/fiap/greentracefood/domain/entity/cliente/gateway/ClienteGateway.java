package com.fiap.greentracefood.domain.entity.cliente.gateway;

import com.fiap.greentracefood.domain.entity.cliente.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ClienteGateway {
//   Cliente buscar(String cpf);
//    Cliente cadastrar(Cliente dto);
//    Cliente atualizar(String cpf,Cliente cliente);
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarClientePorCPF(String cpf);
    Page<Cliente> listarPaginado(Pageable pageable);


}
