package com.fiap.greentracefood.infrastructure.configuration;


import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteCpfGateway;
import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteGateway;
import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.produto.gateway.ProdutoGateway;
import com.fiap.greentracefood.domain.entity.solicitacao.gateway.SolicitacaoExclusaoGateway;
import com.fiap.greentracefood.infrastructure.cliente.gateway.ClienteDataBaseRepository;
import com.fiap.greentracefood.infrastructure.mercadopago.gateway.MercadoPagoGateway;
import com.fiap.greentracefood.infrastructure.messaging.PaymentSender;
import com.fiap.greentracefood.infrastructure.persistence.cliente.ClienteDynamoRepository;
import com.fiap.greentracefood.infrastructure.persistence.cliente.SpringClienteRepository;
import com.fiap.greentracefood.infrastructure.pagamento.gateway.PagamentoDataBaseRepository;
import com.fiap.greentracefood.infrastructure.persistence.pagamento.SpringPagamentoRepository;
import com.fiap.greentracefood.infrastructure.pedido.gateway.PedidoDatabaseRepository;
import com.fiap.greentracefood.infrastructure.pedido.gateway.SpringPedidoRepository;
import com.fiap.greentracefood.infrastructure.persistence.solicitacao.SpringSolicitacaoExclusaoRepository;
import com.fiap.greentracefood.infrastructure.produto.gateway.ProdutoDataBaseRepository;
import com.fiap.greentracefood.infrastructure.persistence.produto.SpringProdutoRepository;
import com.fiap.greentracefood.infrastructure.solicitacao.gateway.SolicitacaoExclusaoDataBaseRepository;
import com.fiap.greentracefood.usecases.cliente.ClienteUseCase;
import com.fiap.greentracefood.usecases.pagamento.PagamentoUseCase;
import com.fiap.greentracefood.usecases.pedido.PedidoUseCase;
import com.fiap.greentracefood.usecases.produto.ProdutoUseCase;
import com.fiap.greentracefood.usecases.solicitacao.SolicitacaoExclusaoUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;


@Configuration
public class BeanConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public ClienteGateway createClienteGateway(SpringClienteRepository springClienteRepository,ModelMapper mapper) {
        return new ClienteDataBaseRepository(springClienteRepository,mapper);
    }
    @Bean
    public ClienteCpfGateway  createClienteCpfGateway(DynamoDbEnhancedClient enhancedClient, @Value("${dynamodb.tablename}") String tableName) {
        return new ClienteDynamoRepository(enhancedClient,tableName);
    }

    @Bean
    public PedidoGateway createPedidoGateway(SpringPedidoRepository springPedidoRepository, ModelMapper mapper) {
        return new PedidoDatabaseRepository(springPedidoRepository,mapper);
    }

    @Bean
    ClienteUseCase createUserCase(ClienteGateway clienteGateway, ClienteCpfGateway clienteCpfGateway) {
        return new ClienteUseCase(clienteGateway,clienteCpfGateway);
    }

    @Bean
    ProdutoGateway createProdutoGateway(SpringProdutoRepository springProdutoRepository,ModelMapper mapper) {
        return new ProdutoDataBaseRepository(springProdutoRepository,mapper);
    }

    @Bean
    PedidoUseCase createPedidoUseCase(PedidoGateway pedidoGateway, ProdutoGateway produtoGateway, ClienteGateway clienteGateway, PaymentSender  paymentSender) {
        return new PedidoUseCase(pedidoGateway,produtoGateway,clienteGateway,paymentSender);
    }
    @Bean
    ProdutoUseCase createProdutoUseCase(ProdutoGateway produtoGateway) {
        return new ProdutoUseCase(produtoGateway);
    }


    @Bean
    PagamentoGateway createPagamentoGateway(SpringPagamentoRepository  springPagamentoRepository, ModelMapper mapper) {
        return new PagamentoDataBaseRepository(springPagamentoRepository,mapper);
    }

    @Bean
    SolicitacaoExclusaoGateway createSolicitacaoExclusaoGateway(SpringSolicitacaoExclusaoRepository solicitacaoExclusaoRepository, ModelMapper mapper) {
        return new SolicitacaoExclusaoDataBaseRepository(solicitacaoExclusaoRepository,mapper);
    }


    @Bean
    PagamentoUseCase createPagamentoUseCase(PagamentoGateway pagamentoGateway,PedidoGateway pedidoGateway,MercadoPagoGateway mercadoPagoGateway) {
        return new PagamentoUseCase(pagamentoGateway,pedidoGateway,mercadoPagoGateway);
    }

    @Bean
    SolicitacaoExclusaoUseCase createSolicitacaoExclusaoUseCase (SolicitacaoExclusaoGateway solicitacaoExclusaoGateway) {
        return new SolicitacaoExclusaoUseCase (solicitacaoExclusaoGateway);
    }
}
