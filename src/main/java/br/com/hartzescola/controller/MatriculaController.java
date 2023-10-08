package br.com.hartzescola.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hartzescola.domain.aluno.AlunoRepository;
import br.com.hartzescola.domain.curso.CursoRepository;
import br.com.hartzescola.domain.curso.relacao.CursoAluno;
import br.com.hartzescola.domain.curso.relacao.CursoAlunoRepository;
import br.com.hartzescola.domain.matricula.DadosMatricula;

@RestController
@RequestMapping("matriculas")
public class MatriculaController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoAlunoRepository cursoAlunoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity matricular(@RequestBody DadosMatricula dados) {
        CursoAluno cursoAluno = new CursoAluno(cursoRepository.getReferenceById(dados.idCurso()), alunoRepository.getReferenceById(dados.idAluno()));
        cursoAlunoRepository.save(cursoAluno);
        
        String mensagem = String.format("aluno %s foi matriculado no curso %s", cursoAluno.getAluno().getNome(), cursoAluno.getCurso().getNome());
        return ResponseEntity.ok().body(mensagem);
    }
} 
