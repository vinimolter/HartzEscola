ALTER TABLE funcionarios ADD senha VARCHAR(255) NOT NULL;
UPDATE funcionarios SET senha = '12345678';