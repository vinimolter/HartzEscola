package br.com.hartzescola.domain.curso;

public record DadosListagemCurso(Long id, String nome, String descricao, int duracao, long valor) {
    
    public DadosListagemCurso(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getDuracao(), curso.getValor());
    }

}
