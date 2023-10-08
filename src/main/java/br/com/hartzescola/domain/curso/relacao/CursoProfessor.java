package br.com.hartzescola.domain.curso.relacao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import br.com.hartzescola.domain.curso.Curso;
import br.com.hartzescola.domain.funcionario.Funcionario;
import lombok.Getter;

@Entity
@Table(name = "curso_professor")
@Getter
public class CursoProfessor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "professor_id") 
    private Funcionario professor;

    public CursoProfessor() {
    }

    public CursoProfessor(CursoProfessor cursoProfessor) {
        this.curso = cursoProfessor.curso;
    }

    public CursoProfessor(Curso curso, Funcionario professor) {
        this.curso = curso;
        this.professor = professor;
    }

    public void alterarProfessorDaMateria(Funcionario professor) {
        if (professor != null){
            this.professor = professor;
        }
    }
    
}
