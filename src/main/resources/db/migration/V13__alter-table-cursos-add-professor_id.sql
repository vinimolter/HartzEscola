ALTER TABLE cursos
ADD professor_id BIGINT,
ADD CONSTRAINT fk_professor FOREIGN KEY (professor_id) REFERENCES funcionarios(id);
