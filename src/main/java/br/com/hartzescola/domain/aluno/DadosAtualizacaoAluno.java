package br.com.hartzescola.domain.aluno;

import javax.validation.constraints.NotNull;

import br.com.hartzescola.domain.endereco.DadosEndereco;

public record DadosAtualizacaoAluno(
    @NotNull
    Long id, 
    String nome, 
    String telefone,
    String email, 
    DadosEndereco endereco) {
    
}
