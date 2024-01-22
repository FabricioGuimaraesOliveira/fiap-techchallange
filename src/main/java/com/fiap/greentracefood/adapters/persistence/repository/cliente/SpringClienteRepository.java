package com.fiap.greentracefood.adapters.persistence.repository.cliente;

import com.fiap.greentracefood.adapters.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringClienteRepository  extends JpaRepository<ClienteEntity, String>, JpaSpecificationExecutor<ClienteEntity> {
}

