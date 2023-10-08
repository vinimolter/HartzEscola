package br.com.hartzescola.domain.curso;

public record DadosDetalhamentoCurso(Long id, String nome, String descricao, int duracao, long valor) {

    public DadosDetalhamentoCurso(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getDuracao(), curso.getValor());
    }

} 