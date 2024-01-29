package com.fiap.greentracefood.application.domain;



import com.fiap.greentracefood.application.enums.StatusPagamento;
import com.fiap.greentracefood.application.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido  {

    private Long id;
    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status = StatusPedido.RECEBIDO;
    private Pagamento pagamento;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    private OffsetDateTime dataFinalizacao;
    private Cliente cliente;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(OffsetDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public OffsetDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(OffsetDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public OffsetDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(OffsetDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    private List<ItemPedido> itens = new ArrayList<>();

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);
        this.subtotal = getItens().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void preparar() {
        setStatus(StatusPedido.PREPARANDO);
        setDataConfirmacao(OffsetDateTime.now());

    }
    public void entregar() {
        setStatus(StatusPedido.PRONTO);
        setDataEntrega(OffsetDateTime.now());
    }

    public void finalizar() {
        setStatus(StatusPedido.FINALIZADO);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public void cancelar() {
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }

    public void setStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
    }

    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

    public boolean podeSerPreparado() {
        return getStatus().podeAlterarPara(status.PREPARANDO)&isPago();
    }
    public boolean podeSerEntregue() {
        return getStatus().podeAlterarPara(status.PRONTO);
    }
    public boolean podeSerCancelado() {
        return getStatus().podeAlterarPara(status.CANCELADO);
    }

    public boolean podeSerFinalizado() {
        return getStatus().podeAlterarPara(status.FINALIZADO)&isPago();
    }
    public boolean isPago() {
        return this.pagamento.getStatus() == StatusPagamento.APROVADO;
    }

    public void inicializarPagamento() {
        this.pagamento =Pagamento.builder()
                .pago(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .build();
    }
}