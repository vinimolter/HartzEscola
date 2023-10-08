package br.com.hartzescola.domain.funcionario.salario;

import javax.validation.constraints.NotNull;

public record DadosAumentoSalario(
    @NotNull
    Long id, 
    @NotNull
    Long valorAumento
) {
}
