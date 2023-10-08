package br.com.hartzescola.domain.curso.relacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hartzescola.domain.aluno.Aluno;
import br.com.hartzescola.domain.curso.Curso;


public interface CursoAlunoRepository extends JpaRepository<CursoAluno, Long>{

    Page<CursoAluno> findAllByCurso(Curso curso, Pageable pageable);

    Page<CursoAluno> findAllByAluno(Aluno aluno, Pageable pageable);

}
