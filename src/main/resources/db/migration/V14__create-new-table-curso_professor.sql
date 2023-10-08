CREATE TABLE curso_professor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    curso_id BIGINT,
    professor_id BIGINT,
    FOREIGN KEY (curso_id) REFERENCES cursos(id),
    FOREIGN KEY (professor_id) REFERENCES funcionarios(id)
);