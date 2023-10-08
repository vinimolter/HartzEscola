package br.com.hartzescola.domain.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Page<Funcionario> findAllByAtivoTrue(Pageable paginacao);

    UserDetails findByEmail(String email);
}
