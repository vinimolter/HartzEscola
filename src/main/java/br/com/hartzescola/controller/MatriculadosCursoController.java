package br.com.hartzescola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hartzescola.domain.curso.Curso;
import br.com.hartzescola.domain.curso.CursoRepository;
import br.com.hartzescola.domain.curso.relacao.CursoAluno;
import br.com.hartzescola.domain.curso.relacao.CursoAlunoRepository;
import br.com.hartzescola.domain.matricula.DadosDetalhamentoMatricula;

@RestController
@RequestMapping("matriculadoscurso")
public class MatriculadosCursoController {

    @Autowired
    private CursoAlunoRepository cursoAlunoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Page<DadosDetalhamentoMatricula>> buscaAlunosMatriculadosNoCurso(@PathVariable Long id, @PageableDefault(size = 10, sort = {}) Pageable paginacao) {
        Curso curso = cursoRepository.findById(id).orElse(null);

    if (curso == null) {
        return ResponseEntity.notFound().build();
    }

    Page<CursoAluno> cursoAlunoPage = cursoAlunoRepository.findAllByCurso(curso, paginacao);
    Page<DadosDetalhamentoMatricula> detalhamentoPage = cursoAlunoPage.map(DadosDetalhamentoMatricula::new);

    return ResponseEntity.ok(detalhamentoPage);
    }
    
}
