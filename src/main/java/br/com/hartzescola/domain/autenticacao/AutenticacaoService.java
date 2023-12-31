package br.com.hartzescola.domain.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hartzescola.domain.usuario.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails user = usuarioRepository.findByEmail(email);

        if (user != null) {
            return user;
        } else {
                throw new UsernameNotFoundException("E-mail não encontrado: " + email);
        }
    }
}