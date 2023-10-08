package br.com.hartzescola.domain.curso.relacao;

import java.io.Serializable;

public class CursoAlunoId implements Serializable{

    private Long curso;
    private Long aluno;

    public CursoAlunoId() {
    }

    public CursoAlunoId(Long curso, Long aluno) {
        this.curso = curso;
        this.aluno = aluno;
    }
    
}
