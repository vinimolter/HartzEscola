package br.com.hartzescola.domain.aluno;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);

    UserDetails findByEmail(String username); 

    Aluno findAlunoByEmail(String email);

}

//Optional<Aluno> findByEmailOptional(String username); 