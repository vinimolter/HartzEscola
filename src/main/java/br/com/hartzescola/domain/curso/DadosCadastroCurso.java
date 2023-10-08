package br.com.hartzescola.domain.curso;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DadosCadastroCurso(
    @NotBlank
    @NotNull
    String nome,
    @NotBlank
    @NotNull
    String descricao,
    @NotNull
    int duracao,
    @NotNull
    long valor
) { 
}
