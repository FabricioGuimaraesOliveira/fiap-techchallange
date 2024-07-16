package com.fiap.greentracefood.infrastructure.pedido.gateway;

import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.infrastructure.persistence.pedido.PedidoEntity;
import com.fiap.greentracefood.infrastructure.persistence.pedido.projection.PedidoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringPedidoRepository extends JpaRepository<PedidoEntity, String>, JpaSpecificationExecutor<PedidoEntity> {


    Page<PedidoEntity> findByClienteCpf(String cpf, Pageable pageable);

//    @Query("SELECT p.codigo AS codigo, p.valorTotal AS valorTotal, p.status AS status, p.pagamento.status AS statusPagamento, c.nome AS nomeCliente, " +
//            "p.dataCriacao AS dataCriacao, p.dataConfirmacao AS dataConfirmacao, p.dataCancelamento AS dataCancelamento, p.dataEntrega AS dataEntrega " +
//            "FROM PedidoEntity p LEFT JOIN p.cliente c WHERE p.codigo = :codigo")
//    Optional<PedidoProjection> findPedidoResumidoByCodigo(@Param("codigo") String codigo);
    @Query("SELECT p.codigo AS codigo, p.valorTotal AS valorTotal, p.status AS status, p.pagamento AS pagamento, c.nome AS nomeCliente, " +
            "p.dataCriacao AS dataCriacao, p.dataConfirmacao AS dataConfirmacao, p.dataCancelamento AS dataCancelamento, p.dataEntrega AS dataEntrega " +
            "FROM PedidoEntity p LEFT JOIN p.cliente c WHERE p.codigo = :codigo")
     Optional<PedidoProjection> findPedidoResumidoByCodigo(@Param("codigo") String codigo);

    Optional<PedidoEntity> findByCodigo(String codigo);

    @Query("SELECT p.codigo AS codigo, p.valorTotal AS valorTotal, p.status AS status, p.status AS statusPagamento, c.nome AS nomeCliente, " +
            "p.dataCriacao AS dataCriacao, p.dataConfirmacao AS dataConfirmacao, p.dataCancelamento AS dataCancelamento, p.dataEntrega AS dataEntrega " +
            "FROM PedidoEntity p LEFT JOIN p.cliente c WHERE p.status = :status")
    Page<PedidoProjection> findPedidosByStatus(@Param("status") StatusPedido status, Pageable pageable);

    @Query("SELECT p FROM PedidoEntity p " +
            "WHERE p.status IN (:statusList) " +
            "AND (:cpfFilter IS NULL OR p.cliente.cpf = :cpfFilter) " + // Adiciona a condição para filtrar por CPF
            "ORDER BY " +
            "CASE " +
            "    WHEN p.status = 'PRONTO' THEN 1 " +
            "    WHEN p.status = 'PREPARANDO' THEN 2 " +
            "    WHEN p.status = 'RECEBIDO' THEN 3 " +
            "    ELSE 4 " +
            "END, " +
            "p.dataCriacao ASC")
    Page<PedidoEntity> findPedidosOrderByStatusAndCpf(List<StatusPedido> statusList, String cpfFilter, Pageable pageable);

}


