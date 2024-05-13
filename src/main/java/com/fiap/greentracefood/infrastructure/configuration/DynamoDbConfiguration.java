package com.fiap.greentracefood.infrastructure.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfiguration {

    @Bean
    @Primary
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(@Value("${dynamodb.tablename}") String tableName) {
        DynamoDbClient dynamoDbClient =
                DynamoDbClient.builder()
                        .region(Region.US_EAST_1)
                        .build();
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }


}