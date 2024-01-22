package com.fiap.greentracefood.adapters.persistence.repository.pagamento;

import com.fiap.greentracefood.adapters.persistence.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface SpringPagamentoRepository extends JpaRepository<PagamentoEntity, Long>, JpaSpecificationExecutor<PagamentoEntity> {
    @Query("SELECT p FROM PagamentoEntity p WHERE p.pedido.codigo= :codigoPedido")
    Optional<PagamentoEntity> findByPedidoCodigo(@Param("codigoPedido") String codigoPedido);


}
