package br.com.hartzescola.domain.funcionario.salario;

import javax.validation.constraints.NotNull;

public record DadosAlterarContaSalario(
    @NotNull
    long id,
    @NotNull
    int contaAgencia, 
    @NotNull
    int contaTipo,
    @NotNull
    int contaNumero
) {
}
