CREATE TABLE PRODUTO_ENTITY(
    id int8 not null,
    nome varchar(255) not null,
    descricao varchar(255) not null,
    preco numeric not null,
    categoria varchar(255),
    constraint produto_entity_pk primary key (id)
);

CREATE TABLE CLIENTE_ENTITY(
    cpf varchar(13) not null,
    nome varchar(255) not null,
    email varchar(255) not null,
    datacadastro timestamp with time zone,
    constraint cliente_entity_pk primary key (cpf)
);

CREATE TABLE PAGAMENTO_ENTITY(
    id int8 not null,
    status varchar(20) not null,
);

CREATE TABLE PEDIDO_ENTITY(
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
    cliente_id varchar(13) not null,
    pagamento_id int8 not null,
    constraint pedido_entity_pk primary key (id),
    constraint cliente_fk foreign key (cliente_id) references CLIENTE_ENTITY(cpf),
    constraint pagamento_fk foreign key (pagamento_id) references PAGAMENTO_ENTITY(id)
);

CREATE TABLE ITEM_PEDIDO_ENTITY(
    id int8 not null,
    preco_unitario numeric not null ,
    preco_total numeric not null,
    quantidade int4 not null,
    observacao varchar(255),
    pedido_id int8 not null,
    produto_id int8 not null,
    constraint item_pedido_entity_pk primary key id,
    constraint pedido_fk foreign key (pedido_id) references PEDIDO_ENTITY(id),
    constraint produto_fk foreign key (produto_id) references PRODUTO_ENTITY(id)
);







