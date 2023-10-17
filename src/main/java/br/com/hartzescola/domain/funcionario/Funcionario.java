package br.com.hartzescola.domain.funcionario;

import br.com.hartzescola.domain.curso.Curso;
import br.com.hartzescola.domain.endereco.Endereco;
import br.com.hartzescola.domain.funcionario.salario.DadosAumentoSalario;
import br.com.hartzescola.domain.funcionario.salario.DadosAlterarContaSalario;
import br.com.hartzescola.domain.funcionario.salario.Salario;
import br.com.hartzescola.domain.usuario.Usuario;

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

public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private EnumCargo cargo;

    @Embedded
    private Endereco endereco;
    private Salario salario;

    // Serve para fazer a relação de um para muitos entre professor e curso
    @OneToMany(mappedBy = "professor")
    private List<Curso> cursosMinistrados;

    // Faz a relação um para um entre funcionário e os dados para login no banco
    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public void adicionarProfessorAoCurso(Curso curso) {
        cursosMinistrados.add(curso);
    }

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.cargo = dados.cargo();
        this.endereco = new Endereco(dados.endereco());
        this.salario = new Salario(dados.salario());
    }

        public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
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

    public void atualizarDadosDaConta(DadosAlterarContaSalario dados) {
        this.salario.atualizarInformacoes(dados);
    }

}