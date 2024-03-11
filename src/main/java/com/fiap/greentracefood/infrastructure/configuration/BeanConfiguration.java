package com.fiap.greentracefood.infrastructure.configuration;


import com.fiap.greentracefood.domain.entity.cliente.gateway.ClienteGateway;
import com.fiap.greentracefood.domain.entity.pagamento.gateway.PagamentoGateway;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.produto.gateway.ProdutoGateway;
import com.fiap.greentracefood.infrastructure.cliente.gateway.ClienteDataBaseRepository;
import com.fiap.greentracefood.infrastructure.mercadopago.gateway.MercadoPagoGateway;
import com.fiap.greentracefood.infrastructure.persistence.cliente.SpringClienteRepository;
import com.fiap.greentracefood.infrastructure.pagamento.gateway.PagamentoDataBaseRepository;
import com.fiap.greentracefood.infrastructure.persistence.pagamento.SpringPagamentoRepository;
import com.fiap.greentracefood.infrastructure.pedido.gateway.PedidoDatabaseRepository;
import com.fiap.greentracefood.infrastructure.pedido.gateway.SpringPedidoRepository;
import com.fiap.greentracefood.infrastructure.produto.gateway.ProdutoDataBaseRepository;
import com.fiap.greentracefood.infrastructure.persistence.produto.SpringProdutoRepository;
import com.fiap.greentracefood.usecases.cliente.ClienteUseCase;
import com.fiap.greentracefood.usecases.pagamento.PagamentoUseCase;
import com.fiap.greentracefood.usecases.pedido.PedidoUseCase;
import com.fiap.greentracefood.usecases.produto.ProdutoUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
    public PedidoGateway createPedidoGateway(SpringPedidoRepository springPedidoRepository, ModelMapper mapper) {
        return new PedidoDatabaseRepository(springPedidoRepository,mapper);
    }

    @Bean
    ClienteUseCase createUserCase(ClienteGateway clienteGateway) {
        return new ClienteUseCase(clienteGateway);
    }

    @Bean
    ProdutoGateway createProdutoGateway(SpringProdutoRepository springProdutoRepository,ModelMapper mapper) {
        return new ProdutoDataBaseRepository(springProdutoRepository,mapper);
    }

    @Bean
    PedidoUseCase createPedidoUseCase(PedidoGateway pedidoGateway,ProdutoGateway produtoGateway,ClienteGateway clienteGateway) {
        return new PedidoUseCase(pedidoGateway,produtoGateway,clienteGateway);
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
    PagamentoUseCase createPagamentoUseCase(PagamentoGateway pagamentoGateway,PedidoGateway pedidoGateway,MercadoPagoGateway mercadoPagoGateway) {
        return new PagamentoUseCase(pagamentoGateway,pedidoGateway,mercadoPagoGateway);
    }
}
