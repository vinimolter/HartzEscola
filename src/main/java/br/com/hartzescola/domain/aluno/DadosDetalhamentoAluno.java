package br.com.hartzescola.domain.aluno;

import br.com.hartzescola.domain.endereco.Endereco;

public record DadosDetalhamentoAluno(Long id, String nome, String telefone, String cpf, Endereco endereco) {

    public DadosDetalhamentoAluno (Aluno aluno){
        this(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getCpf(), aluno.getEndereco());
    } 
    
}
