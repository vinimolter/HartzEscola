package br.com.hartzescola.controller;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hartzescola.domain.curso.Curso;
import br.com.hartzescola.domain.curso.CursoRepository;
import br.com.hartzescola.domain.curso.relacao.CursoProfessor;
import br.com.hartzescola.domain.curso.relacao.CursoProfessorDTO;
import br.com.hartzescola.domain.curso.relacao.CursoProfessorRepository;
import br.com.hartzescola.domain.funcionario.DadosAlocarProfessor;
import br.com.hartzescola.domain.funcionario.Funcionario;
import br.com.hartzescola.domain.funcionario.FuncionarioRepository;

@RestController
@RequestMapping("professor")
public class ProfessorController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CursoProfessorRepository cursoProfessorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity alocarProfessor(@RequestBody DadosAlocarProfessor dados) {
        Curso curso = cursoRepository.findById(dados.idCurso()).get();
        Funcionario professor = funcionarioRepository.findById(dados.idProfessor()).get();

        if (curso.getProfessor() != null) {
            throw new RuntimeException("O curso já possui um professor alocado!");
        } else {

            CursoProfessor cursoProfessor = new CursoProfessor(curso, professor);
            cursoProfessorRepository.save(cursoProfessor);

            curso.setProfessor(professor);
            cursoRepository.save(curso);

            String mensagem = String.format("O professor %s foi designado para o curso curso %s", professor.getNome(),
                    curso.getNome());
            return ResponseEntity.ok().body(mensagem);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CursoProfessorDTO>> buscaCursosMinistrados(@PathVariable Long id) {
        Funcionario professor = funcionarioRepository.findById(id).orElse(null);

        if (professor == null) {
            return ResponseEntity.notFound().build();
        }
        Long professorId = professor.getId();

        List<CursoProfessor> cursoProfessorPage = cursoProfessorRepository.findAllByProfessorId(professorId);

        List<CursoProfessorDTO> cursoProfessorDTOs = cursoProfessorPage.stream()
                .map(cursoProfessor -> new CursoProfessorDTO(
                        cursoProfessor.getId(),
                        cursoProfessor.getCurso().getNome()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cursoProfessorDTOs);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAlocarProfessor dados) {
        var professor = funcionarioRepository.getReferenceById(dados.idProfessor());
        Curso curso = cursoRepository.findById(dados.idCurso()).get();

        Optional<CursoProfessor> cursoProfessorOptional = cursoProfessorRepository.findByCursoId(dados.idCurso());

        if (cursoProfessorOptional.isPresent()) {
            CursoProfessor cursoProfessor = cursoProfessorOptional.get();

            cursoProfessorRepository.delete(cursoProfessor);

            CursoProfessor novoCursoProfessor = new CursoProfessor(cursoProfessor.getCurso(), professor);

            cursoProfessorRepository.save(novoCursoProfessor);
            curso.setProfessor(professor);

            String mensagem = String.format("O novo professor do curso %s é %s",
                    cursoProfessorOptional.get().getCurso().getNome(),
                    cursoProfessorOptional.get().getProfessor().getNome());

            return ResponseEntity.ok(mensagem);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}