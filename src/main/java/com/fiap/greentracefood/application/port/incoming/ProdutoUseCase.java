package com.fiap.greentracefood.application.port.incoming;

import com.fiap.greentracefood.application.domain.Produto;
import com.fiap.greentracefood.application.enums.Categoria;

import java.util.List;

public interface ProdutoUseCase {
    public void consultaPaginada();
    public List<Produto> consultaByCategoria(Categoria categoria);
    public Produto detalhar(Long id);
    public Produto cadastrar(Produto dto);
    public Produto alterar(Produto dto);
    public void excluir(Long id);
}
