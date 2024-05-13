package com.fiap.greentracefood.infrastructure.persistence.cliente;
import java.util.Objects;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ClientCpfEntity {
    private String cpf;
    private String id;

    public ClientCpfEntity(){}

    public ClientCpfEntity(String cpf, String id) {
        this.cpf = cpf;
        this.id = id;
    }



    @DynamoDbPartitionKey
    @DynamoDbAttribute("cpf")
    public String getCpf() { return this.cpf; }

    public String getId() { return this.id; }

    public void setCpf(String cpf) { this.cpf = cpf;  }

    public void setId(String id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientCpfEntity that)) return false;
        return Objects.equals(getCpf(), that.getCpf()) && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf(), getId());
    }

    @Override
    public String toString() {
        return "Customer [cpf=" + cpf + ", id=" + id + "]";
    }
}
