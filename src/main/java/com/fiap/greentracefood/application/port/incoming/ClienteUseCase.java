package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Cliente;

public interface ClienteUseCase {
    //public Page<ClienteDto> consultaPaginada(Pageable paginacao);
    public Cliente detalhar(String cpf);
    public Cliente cadastrar(Cliente dto);
    public Cliente alterar(String email, Cliente dto);
    public void excluir(String email);
}
