CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ativo TINYINT,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    funcionario_id BIGINT,
    aluno_id BIGINT,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios (id),
    FOREIGN KEY (aluno_id) REFERENCES alunos (id)
);
