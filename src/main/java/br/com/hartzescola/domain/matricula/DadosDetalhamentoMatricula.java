package br.com.hartzescola.domain.matricula;

import br.com.hartzescola.domain.curso.relacao.CursoAluno;

public record DadosDetalhamentoMatricula(Long idCurso, Long idAluno, String nomeCurso, String nomeAluno) {

    public DadosDetalhamentoMatricula(CursoAluno cursoAluno){
        this(cursoAluno.getCurso().getId(), cursoAluno.getAluno().getId(), cursoAluno.getCurso().getNome(), cursoAluno.getAluno().getNome());
    }

}
