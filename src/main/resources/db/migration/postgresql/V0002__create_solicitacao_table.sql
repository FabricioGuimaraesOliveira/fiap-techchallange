CREATE TABLE solicitacao (
    id int8 not null,
    cpf VARCHAR(14) NOT NULL,                      
    nome VARCHAR(255) NOT NULL,                    
    endereco VARCHAR(255) NOT NULL,                
    telefone VARCHAR(20) NOT NULL,                 
    data_solicitacao TIMESTAMP WITH TIME ZONE,  
    numero_acompanhamento int8 not null,
    constraint solicitacao_pk primary key (id)          
);

CREATE SEQUENCE SOLICITACAO_SEQUENCE;
