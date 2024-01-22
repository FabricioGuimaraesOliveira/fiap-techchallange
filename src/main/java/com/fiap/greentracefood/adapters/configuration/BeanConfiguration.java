package com.fiap.greentracefood.adapters.configuration;

import com.fiap.greentracefood.application.port.incoming.ClienteUseCase;
import com.fiap.greentracefood.application.port.incoming.PagamentoUseCase;
import com.fiap.greentracefood.application.port.incoming.ProdutoUseCase;
import com.fiap.greentracefood.application.port.outgoing.ClienteRepositoryPort;
import com.fiap.greentracefood.application.port.outgoing.PagamentoRepositoryPort;
import com.fiap.greentracefood.application.port.outgoing.PedidoRepositoryPort;
import com.fiap.greentracefood.application.port.outgoing.ProdutoRepositoryPort;
import com.fiap.greentracefood.application.services.ClienteService;
import com.fiap.greentracefood.application.services.PagamentoService;
import com.fiap.greentracefood.application.services.PedidoService;
import com.fiap.greentracefood.application.services.ProdutoService;
import com.fiap.greentracefood.application.validators.CpfValidator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Bean
    ClienteService instanceClienteService(ClienteRepositoryPort clienteRepositoryPort, CpfValidator cpfValidator) {
        return new ClienteService(clienteRepositoryPort,cpfValidator);
    }

    @Bean
    ProdutoService instanceProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        return new ProdutoService(produtoRepositoryPort);
    }
    @Bean
    PedidoService instancePedidoService(ClienteUseCase clienteUseCase, ProdutoUseCase produtoUseCase, PedidoRepositoryPort pedidoRepositoryPort) {
        return new PedidoService(pedidoRepositoryPort,produtoUseCase,clienteUseCase);
    }

    @Bean
    PagamentoService instancePagamentoService(PagamentoRepositoryPort pagamentoRepositoryPorte) {
        return new PagamentoService(pagamentoRepositoryPorte);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public CpfValidator cpfValidator() {
        return new CpfValidator();
    }
}
