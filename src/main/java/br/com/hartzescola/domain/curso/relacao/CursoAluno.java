package br.com.hartzescola.domain.curso.relacao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.hartzescola.domain.aluno.Aluno;
import br.com.hartzescola.domain.curso.Curso;
import lombok.Getter;

@Entity
@Table(name = "curso_aluno")
@IdClass(CursoAlunoId.class)
@Getter
public class CursoAluno implements Serializable{
 
    @Id
    @ManyToOne
    private Curso curso;

    @Id
    @ManyToOne
    private Aluno aluno; 

    public CursoAluno() {
    }

    public CursoAluno(Curso curso, Aluno aluno) {
        this.curso = curso;
        this.aluno = aluno;
    }

}
