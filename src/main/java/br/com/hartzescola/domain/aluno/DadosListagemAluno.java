package br.com.hartzescola.domain.aluno;

import br.com.hartzescola.domain.endereco.Endereco;

public record DadosListagemAluno(Long id, String nome, String telefone, String cpf, String email, Endereco endereco) {

    public DadosListagemAluno (Aluno aluno){
        this(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getCpf(), aluno.getEmail(), aluno.getEndereco());
    }
    
}
