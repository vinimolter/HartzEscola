package br.com.hartzescola.domain.usuario;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hartzescola.domain.aluno.Aluno;
import br.com.hartzescola.domain.aluno.DadosAtualizacaoAluno;
import br.com.hartzescola.domain.aluno.DadosCadastroAluno;
import br.com.hartzescola.domain.funcionario.DadosAtualizacaoFuncionario;
import br.com.hartzescola.domain.funcionario.DadosCadastroFuncionario;
import br.com.hartzescola.domain.funcionario.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean ativo;
    private String email;
    private String senha;

    public Usuario(DadosCadastroFuncionario dados) {
        this.email = dados.email();
        this.ativo = true;
        this.senha = dados.senha();
    }

    public Usuario(DadosCadastroAluno dados) {
        this.email = dados.email();
        this.ativo = true;
        this.senha = dados.senha();
    }

    @OneToOne
    private Funcionario funcionario;

    @OneToOne
    private Aluno aluno;

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarEmail(DadosAtualizacaoFuncionario dados) {
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }

    public void atualizarEmailAluno(@Valid DadosAtualizacaoAluno dados) {
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        if (this.funcionario != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.funcionario.getCargo()));
        }
        if (this.aluno != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ALUNO"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}