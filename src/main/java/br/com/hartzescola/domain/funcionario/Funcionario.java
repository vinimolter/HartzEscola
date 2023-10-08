package br.com.hartzescola.domain.funcionario;

import br.com.hartzescola.domain.curso.Curso;
import br.com.hartzescola.domain.endereco.Endereco;
import br.com.hartzescola.domain.funcionario.salario.DadosAumentoSalario;
import br.com.hartzescola.domain.funcionario.salario.DadosAlterarContaSalario;
import br.com.hartzescola.domain.funcionario.salario.Salario;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "funcionarios")
@Entity(name = "Funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private boolean ativo;
    private String senha;

    @Enumerated(EnumType.STRING)
    private EnumCargo cargo;

    @Embedded
    private Endereco endereco;
    private Salario salario;

    // Serve para fazer a relação de um para muitos entre professor e curso
    @OneToMany(mappedBy = "professor")
    private List<Curso> cursosMinistrados;

    public void adicionarProfessorAoCurso(Curso curso) {
        cursosMinistrados.add(curso);
    }

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.cargo = dados.cargo();
        this.senha = dados.senha();
        this.endereco = new Endereco(dados.endereco());
        this.salario = new Salario(dados.salario());
        this.ativo = true;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void atualizarSalario(@Valid DadosAumentoSalario dados) {
        if (dados.valorAumento() != null && dados.valorAumento() > 0) {
            this.salario.atualizarSalario(dados.valorAumento());
        }
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarDadosDaConta(DadosAlterarContaSalario dados) {

        this.salario.atualizarInformacoes(dados);

    }

    // Metodos para fazer login
    // Metodo antigo
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (cargo != null) {
            // Cria a permissão com base no cargo do funcionário
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + cargo.toString()));
        } else {
            // Se o funcionário não tiver cargo retorna uma lista vazia sem permissão nenhuma
            return Collections.emptyList();
        }
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