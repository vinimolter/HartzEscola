create table alunos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    telefone varchar(20) not null,
    cpf varchar(11) not null unique,
    email varchar(100) not null unique,
    data_nascismento varchar(10) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    ativo tinyint,

    primary key(id)

);