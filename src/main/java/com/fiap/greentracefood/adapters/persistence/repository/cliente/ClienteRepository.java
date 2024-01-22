package com.fiap.greentracefood.adapters.persistence.repository.cliente;

import com.fiap.greentracefood.adapters.persistence.entity.ClienteEntity;
import com.fiap.greentracefood.application.domain.Cliente;
import com.fiap.greentracefood.application.port.outgoing.ClienteRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ClienteRepository implements ClienteRepositoryPort {
private final SpringClienteRepository clienteRepository;
private final ModelMapper modelMapper ;
    public ClienteRepository(SpringClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }
    @Transactional()
    @Override
    public Cliente salvar(Cliente cliente) {
        var clienteEntity = clienteRepository.saveAndFlush(modelMapper.map(cliente, ClienteEntity.class));
        return modelMapper.map(clienteEntity, Cliente.class);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Cliente> buscarClientePorCPF(String cpf) {
        var clienteEntity = clienteRepository.findById(cpf);
        return clienteEntity.map(entity -> modelMapper.map(entity, Cliente.class));

    }
}
