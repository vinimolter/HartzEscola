package br.com.hartzescola.domain.funcionario.salario;

import javax.validation.constraints.NotNull;

public record DadosSalario(
    @NotNull
    long valorSalario, 
    @NotNull
    int contaAgencia, 
    @NotNull
    int contaTipo, 
    @NotNull
    int contaNumero) {
    
}
