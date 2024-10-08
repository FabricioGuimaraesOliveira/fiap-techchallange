package com.fiap.greentracefood.infrastructure.produto.gateway;


import com.fiap.greentracefood.domain.entity.produto.enums.Categoria;
import com.fiap.greentracefood.domain.entity.produto.gateway.ProdutoGateway;
import com.fiap.greentracefood.domain.entity.produto.model.Produto;
import com.fiap.greentracefood.infrastructure.persistence.produto.ProdutoEntity;
import com.fiap.greentracefood.infrastructure.persistence.produto.SpringProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class ProdutoDataBaseRepository implements ProdutoGateway {
    private final SpringProdutoRepository springProdutoRepository;
    private final ModelMapper modelMapper;

    public ProdutoDataBaseRepository(SpringProdutoRepository springProdutoRepository, ModelMapper modelMapper) {
        this.springProdutoRepository = springProdutoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional()
    public Produto cadastrar(Produto produto) {
        ProdutoEntity produtoEntity = modelMapper.map(produto, ProdutoEntity.class);
        return modelMapper.map(springProdutoRepository.save(produtoEntity), Produto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Produto> consultar(Long id) {
        var produtoOptional = springProdutoRepository.findById(id);
        return produtoOptional.map(entity -> modelMapper.map(entity, Produto.class));

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Produto> consultarPorCategoria(Categoria categoria, Pageable pageable) {
        Page<ProdutoEntity> produtosPage = springProdutoRepository.findByCategoria(categoria, pageable);
        return produtosPage.map(entity -> modelMapper.map(entity, Produto.class));

    }
    @Override
    @Transactional(readOnly = true)
    public Map<Long, Produto> consultarPorIds(List<Long> ids) {
        List<ProdutoEntity> produtosEntities = springProdutoRepository.findAllById(ids);
        return produtosEntities.stream()
                .collect(Collectors.toMap(
                        entity -> entity.getId(),
                        entity -> modelMapper.map(entity, Produto.class)
                ));
    }

    @Override
    @Transactional()
    public Produto atualizar(Long id, Produto produto) {
        ProdutoEntity produtoEntity = modelMapper.map(produto, ProdutoEntity.class);
        return modelMapper.map(springProdutoRepository.save(produtoEntity), Produto.class);

    }

    @Override
    public Boolean existsById(Long id) {
        return springProdutoRepository.existsById(id);
    }

    @Override
    @Transactional()
    public void excluir(Long id) {
        springProdutoRepository.deleteById(id);
    }
}

