package br.com.hartzescola.domain.curso;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    Page<Curso> findAllByAtivoTrue(Pageable paginacao);

    // Page<Curso> findAllByProfessorId(Long professorId, Pageable pageable);  
    //List<Curso> findAllByProfessorId(Long professorId);  
}
