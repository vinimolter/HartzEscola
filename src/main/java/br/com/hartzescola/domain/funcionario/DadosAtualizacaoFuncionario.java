package br.com.hartzescola.domain.funcionario;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.hartzescola.domain.endereco.DadosEndereco;

public record DadosAtualizacaoFuncionario(
    @NotNull(message = "id is required")
    Long id, 
    String nome, 
    String telefone,
    String email, 
    DadosEndereco endereco) {
}
