package com.fiap.greentracefood.infrastructure.persistence.cliente;

import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteCpfGateway;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Random;
import java.util.UUID;

public class ClienteDynamoRepository  implements ClienteCpfGateway {
    private DynamoDbEnhancedClient enhancedClient;
    private DynamoDbTable<ClientCpfEntity> table;
    private final Random random;
    public ClienteDynamoRepository(DynamoDbEnhancedClient enhancedClient,  String tableName) {
        this.enhancedClient = enhancedClient;
        this.table = enhancedClient.table(tableName, TableSchema.fromBean(ClientCpfEntity.class));
        this.random = new Random();
    }
    @Override
    public void save(String cpf) {
        table.putItem(new ClientCpfEntity(cpf, UUID.randomUUID().toString()));
    }


}
