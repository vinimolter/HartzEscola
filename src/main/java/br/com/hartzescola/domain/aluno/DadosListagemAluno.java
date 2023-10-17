package br.com.hartzescola.domain.aluno;

import br.com.hartzescola.domain.endereco.Endereco;

public record DadosListagemAluno(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

    public DadosListagemAluno (Aluno aluno){
        this(aluno.getId(), aluno.getNome(), aluno.getUsuario().getEmail(), aluno.getTelefone(), aluno.getCpf(), aluno.getEndereco());
    }
    
}
