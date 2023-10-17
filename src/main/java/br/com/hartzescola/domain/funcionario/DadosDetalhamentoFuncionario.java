package br.com.hartzescola.domain.funcionario;

import br.com.hartzescola.domain.endereco.Endereco;

public record DadosDetalhamentoFuncionario(Long id, String nome, String telefone, String cpf, EnumCargo cargo, Endereco endereco) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getTelefone(), funcionario.getCpf(), funcionario.getCargo(), funcionario.getEndereco());
    }
    
}
