CREATE TABLE curso_aluno (
    curso_id BIGINT NOT NULL,
    aluno_id BIGINT NOT NULL,
    PRIMARY KEY (curso_id, aluno_id),
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos(id),
    CONSTRAINT fk_aluno FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);