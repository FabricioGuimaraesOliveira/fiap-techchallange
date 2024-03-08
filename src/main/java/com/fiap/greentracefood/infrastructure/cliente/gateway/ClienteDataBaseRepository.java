package com.fiap.greentracefood.infrastructure.cliente.gateway;

import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteGateway;
import com.fiap.greentracefood.domain.entity.cliente.model.Cliente;
import com.fiap.greentracefood.infrastructure.persistence.cliente.ClienteEntity;
import com.fiap.greentracefood.infrastructure.persistence.cliente.SpringClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class ClienteDataBaseRepository implements ClienteGateway {
private final SpringClienteRepository clienteRepository;
private final ModelMapper modelMapper ;
    public ClienteDataBaseRepository(SpringClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional()
    public Cliente salvar(Cliente cliente) {
        var clienteEntity = clienteRepository.saveAndFlush(modelMapper.map(cliente, ClienteEntity.class));
        return modelMapper.map(clienteEntity, Cliente.class);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarClientePorCPF(String cpf) {
        var clienteEntity = clienteRepository.findById(cpf);
        return clienteEntity.map(entity -> modelMapper.map(entity, Cliente.class));

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> listarPaginado(Pageable pageable) {
        Page<ClienteEntity> clienteEntitiesPage = clienteRepository.findAll(pageable);
        return clienteEntitiesPage.map(entity -> modelMapper.map(entity, Cliente.class));
    }

}
