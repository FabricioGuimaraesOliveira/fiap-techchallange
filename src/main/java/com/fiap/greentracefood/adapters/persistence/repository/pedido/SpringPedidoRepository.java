package com.fiap.greentracefood.adapters.persistence.repository.pedido;

import com.fiap.greentracefood.adapters.persistence.entity.PedidoEntity;
import com.fiap.greentracefood.application.enums.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface SpringPedidoRepository extends JpaRepository<PedidoEntity, String>, JpaSpecificationExecutor<PedidoEntity> {
    @Query("SELECT p FROM PedidoEntity p " +
            "JOIN FETCH p.cliente c " +
            "WHERE c.cpf = :cpf " +
            "AND p.dataCriacao >= :dataCadastroInicio " +
            "AND p.dataCriacao <= :dataCadastroFim")
    List<PedidoEntity> findByCpfAndDataCadastro(@Param("cpf") String cpf,
                                                @Param("dataCadastroInicio") OffsetDateTime dataCadastroInicio,
                                                @Param("dataCadastroFim") OffsetDateTime dataCadastroFim);

    Page<PedidoEntity> findByClienteCpf(String cpf, Pageable pageable);

    @Query("SELECT p.codigo AS codigo, p.valorTotal AS valorTotal, p.status AS status, p.pagamento.status AS statusPagamento, c.nome AS nomeCliente, " +
            "p.dataCriacao AS dataCriacao, p.dataConfirmacao AS dataConfirmacao, p.dataCancelamento AS dataCancelamento, p.dataEntrega AS dataEntrega " +
            "FROM PedidoEntity p LEFT JOIN p.cliente c WHERE p.codigo = :codigo")
    Optional<PedidoProjection> findPedidoResumidoByCodigo(@Param("codigo") String codigo);

    Optional<PedidoEntity> findByCodigo(String codigo);

    @Query("SELECT p.codigo AS codigo, p.valorTotal AS valorTotal, p.status AS status, p.pagamento.status AS statusPagamento, c.nome AS nomeCliente, " +
            "p.dataCriacao AS dataCriacao, p.dataConfirmacao AS dataConfirmacao, p.dataCancelamento AS dataCancelamento, p.dataEntrega AS dataEntrega " +
            "FROM PedidoEntity p JOIN p.cliente c WHERE p.status = :status")
    Page<PedidoProjection> findPedidosByStatus(@Param("status") StatusPedido status, Pageable pageable);

}


