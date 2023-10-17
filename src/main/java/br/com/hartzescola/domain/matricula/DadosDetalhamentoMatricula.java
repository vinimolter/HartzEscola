package br.com.hartzescola.domain.matricula;

import br.com.hartzescola.domain.curso.relacao.CursoAluno;

public record DadosDetalhamentoMatricula(String nomeCurso, String nomeProfessor) {

    public DadosDetalhamentoMatricula(CursoAluno cursoAluno){
        this(cursoAluno.getCurso().getNome(), cursoAluno.getCurso().getProfessor().getNome());
    }

}
