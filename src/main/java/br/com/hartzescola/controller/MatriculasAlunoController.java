package br.com.hartzescola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hartzescola.domain.aluno.Aluno;
import br.com.hartzescola.domain.aluno.AlunoRepository;
import br.com.hartzescola.domain.curso.relacao.CursoAluno;
import br.com.hartzescola.domain.curso.relacao.CursoAlunoRepository;
import br.com.hartzescola.domain.matricula.DadosDetalhamentoMatricula;
import br.com.hartzescola.domain.usuario.Usuario;
import br.com.hartzescola.domain.usuario.UsuarioRepository;

@RestController
public class MatriculasAlunoController { 

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoAlunoRepository cursoAlunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/{id}")
    @RequestMapping("matriculasaluno")
    public ResponseEntity buscaAsMatriculasDoAluno (@PathVariable Long id, @PageableDefault(size = 10, sort = {}) Pageable paginacao){
        Aluno aluno = alunoRepository.findById(id).orElse(null);

        if (aluno == null) {
            return ResponseEntity.notFound().build(); 
        }

        Page<CursoAluno> cursoAlunoPage = cursoAlunoRepository.findAllByAluno(aluno, paginacao);
        Page<DadosDetalhamentoMatricula> detalhamentoPage = cursoAlunoPage.map(DadosDetalhamentoMatricula::new);

        return ResponseEntity.ok(detalhamentoPage);

    }

    @GetMapping
    @RequestMapping("meuscursos")
    public ResponseEntity buscaCursosDoAluno(@AuthenticationPrincipal UserDetails userDetails, @PageableDefault(size = 10, sort = {}) Pageable paginacao) {
    if (userDetails == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    String username = userDetails.getUsername();

    Usuario usuario = usuarioRepository.findUsuarioByEmail(username);

        if (usuario == null) {
            return ResponseEntity.notFound().build(); 
        }
        Aluno aluno = usuario.getAluno();
        Page<CursoAluno> cursoAlunoPage = cursoAlunoRepository.findAllByAluno(aluno, paginacao);
        Page<DadosDetalhamentoMatricula> detalhamentoPage = cursoAlunoPage.map(DadosDetalhamentoMatricula::new);

    return ResponseEntity.ok(detalhamentoPage);
}




}
