package br.com.hartzescola.domain.funcionario;

import javax.validation.constraints.NotNull;

public record DadosAlocarProfessor(
    @NotNull
    Long idProfessor,
    @NotNull
    Long idCurso
) {
}
