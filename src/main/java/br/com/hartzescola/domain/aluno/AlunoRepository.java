package br.com.hartzescola.domain.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    // Page<Aluno> findAllByAtivoTrue(Pageable paginacao);

}