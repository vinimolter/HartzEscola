create table cursos(
    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    descricao varchar(255) not null,
    duracao int not null,
    valor bigint not null,
    ativo tinyint,
    primary key(id)
);