package br.com.hartzescola.domain.aluno;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.hartzescola.domain.endereco.DadosEndereco;

public record DadosCadastroAluno(
    @NotBlank
    @NotNull
    String nome,
    @NotBlank
    @NotNull
    String telefone,
    @NotBlank
    @NotNull
    @Pattern(regexp = "^\\d{11}$")
    String cpf,
    @NotBlank
    @NotNull
    @Email
    String email,
    @NotBlank
    @NotNull
    String senha,
    @NotNull
    @Valid
    DadosEndereco endereco){
    }
