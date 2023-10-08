package br.com.hartzescola.domain.curso.relacao;

public record CursoProfessorDTO(Long id, String nome) {

    public CursoProfessorDTO (CursoProfessor cursoProfessor){
        this(cursoProfessor.getId(), cursoProfessor.getCurso().toString());
    }

}