package br.com.hartzescola.domain.funcionario;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.hartzescola.domain.endereco.DadosEndereco;
import br.com.hartzescola.domain.funcionario.salario.DadosSalario;


public record DadosCadastroFuncionario(
    @NotBlank
    @NotNull
    String nome, 
    @NotBlank
    @NotNull
    String telefone,
    @NotBlank
    @Pattern(regexp = "^\\d{11}$")
    @NotBlank
    String cpf,
    @NotBlank
    @Email
    @NotNull
    String email,
    @NotBlank
    @NotNull 
    String senha,
    @NotNull
    EnumCargo cargo, 
    @NotNull
    @Valid
    DadosEndereco endereco,
    @NotNull
    @Valid
    DadosSalario salario) { 
}
 