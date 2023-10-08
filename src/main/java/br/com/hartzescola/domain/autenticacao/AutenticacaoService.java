package br.com.hartzescola.domain.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hartzescola.domain.aluno.AlunoRepository;
import br.com.hartzescola.domain.funcionario.FuncionarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private AlunoRepository alunoRepository;

   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails funcionario = funcionarioRepository.findByEmail(email);

        if (funcionario != null) {
            return funcionario;
        } else {
            UserDetails aluno = alunoRepository.findByEmail(email);

            if (aluno != null) {
                return aluno;
            } else {
                throw new UsernameNotFoundException("E-mail não encontrado: " + email);
            }
        }
    }
}