package br.com.hartzescola.domain.curso.relacao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoProfessorRepository extends JpaRepository<CursoProfessor, Long> { 

    List<CursoProfessor> findAllByProfessorId(Long professorId); 

    Optional<CursoProfessor> findByCursoId(Long cursoId);
    
}
