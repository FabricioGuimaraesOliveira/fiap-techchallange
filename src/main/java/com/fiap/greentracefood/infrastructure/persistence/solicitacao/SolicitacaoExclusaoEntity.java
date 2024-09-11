package com.fiap.greentracefood.infrastructure.persistence.solicitacao;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "solicitacao")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SolicitacaoExclusaoEntity {

	@Id
    @SequenceGenerator(name = "solicitacao_seq", sequenceName = "solicitacao_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_seq")
    @EqualsAndHashCode.Include
    private Long id;
    
    @Size(max=11, min=11)
    @CPF
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String telefone;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataSolicitacao;
    
    @Column(nullable = false)
    private Long numeroAcompanhamento;

}