package com.fiap.greentracefood.infrastructure.solicitacao.dto;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class SolicitacaoExclusaoRequestDTO {

    @Schema(description = "CPF do Cliente", example = "34511780013")
    @NotNull(message = "CPF do Cliente não pode ser nulo")
    @CPF(message = "CPF Invalido")
    private String cpf;
    
    @Schema(description = "Nome do Cliente", example = "José da Silva")
    @NotNull(message = "O Nome do Cliente não pode ser nulo")
    private String nome;
    
    @Schema(description = "Endereço do Cliente", example = "Rua José da Silva, s/n")
    @NotNull(message = "O Endereço do Cliente não pode ser nulo")
    private String endereco;
    
    @Schema(description = "Telefone do Cliente", example = "11987654321")
    @NotNull(message = "O Telefone do Cliente não pode ser nulo")
    private String telefone;
    
}