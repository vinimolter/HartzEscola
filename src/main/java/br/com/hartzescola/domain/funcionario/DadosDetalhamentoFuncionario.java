package br.com.hartzescola.domain.funcionario;

import br.com.hartzescola.domain.endereco.Endereco;

public record DadosDetalhamentoFuncionario(Long id, String nome, String telefone, String cpf, String email, EnumCargo cargo, Endereco endereco) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getTelefone(), funcionario.getCpf(), funcionario.getEmail(), funcionario.getCargo(), funcionario.getEndereco());
    }
    
}
