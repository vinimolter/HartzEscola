package br.com.hartzescola.domain.matricula;

import lombok.NonNull;

public record DadosMatricula(
    @NonNull
    long idCurso,
    @NonNull
    long idAluno
) {   
}
