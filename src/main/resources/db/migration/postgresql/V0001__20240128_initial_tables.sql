CREATE TABLE produtos(
    id int8 not null,
    nome varchar(255) not null,
    descricao varchar(255) not null,
    preco numeric not null,
    categoria varchar(255),
    constraint produtos_pk primary key (id)
);

CREATE TABLE clientes(
    cpf varchar(13) not null,
    nome varchar(255) not null,
    email varchar(255) not null,
    datacadastro timestamp with time zone,
    constraint clientes_pk primary key (cpf)
);

CREATE TABLE pagamentos(
    id int8 not null,
    status varchar(20) not null,
    constraint pagamentos_pk primary key (id)
);

CREATE TABLE pedidos(
    id int8 not null,
    codigo varchar(255) not null,
    subtotal numeric not null,
    taxa_frete numeric null,
    valor_total numeric not null,
    status varchar(20) not null,
    data_criacao timestamp with time zone,
    data_confirmacao timestamp with time zone,
    data_entrega timestamp with time zone,
    data_finalizado timestamp with time zone,
    data_cancelamento timestamp with time zone,
    cliente_id varchar(13) not null,
    pagamento_id int8 not null,
    constraint pedidos_pk primary key (id),
    constraint clientes_fk foreign key (cliente_id) references clientes(cpf),
    constraint pagamentos_fk foreign key (pagamento_id) references pagamentos(id)
);

CREATE TABLE pedido_itens(
    id int8 not null,
    preco_unitario numeric not null ,
    preco_total numeric not null,
    quantidade int4 not null,
    observacao varchar(255),
    pedido_id int8 not null,
    produto_id int8 not null,
    constraint pedido_itens_pk primary key (id),
    constraint pedidos_fk foreign key (pedido_id) references pedidos(id),
    constraint produtos_fk foreign key (produto_id) references produtos(id)
);

CREATE SEQUENCE PRODUTO_SEQUENCE;
CREATE SEQUENCE PEDIDO_SEQUENCE;
CREATE SEQUENCE PAGAMENTO_SEQUENCE;

INSERT INTO produtos (id, nome, descricao, preco, categoria)
values ( nextval('produto_sequence'), 'combo 1 ', ' lanche, refrigerante e batata frita', 25, 'LANCHE' );

INSERT INTO produtos (id, nome, descricao, preco, categoria)
values ( nextval('produto_sequence'), 'combo 2 ', ' lanche, refrigerante e batata frita', 32, 'LANCHE' );

INSERT INTO produtos (id, nome, descricao, preco, categoria)
values ( nextval('produto_sequence'), 'refrigerante ', ' refrigerante especial', 12, 'BEBIDA' );

INSERT INTO produtos (id, nome, descricao, preco, categoria)
values ( nextval('produto_sequence'), 'batata frita', ' batata frita ', 12, 'ACOMPANHAMENTO' );






