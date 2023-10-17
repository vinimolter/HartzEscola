package br.com.hartzescola.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hartzescola.domain.aluno.Aluno;
import br.com.hartzescola.domain.aluno.AlunoRepository;
import br.com.hartzescola.domain.aluno.DadosAtualizacaoAluno;
import br.com.hartzescola.domain.aluno.DadosCadastroAluno;
import br.com.hartzescola.domain.aluno.DadosDetalhamentoAluno;
import br.com.hartzescola.domain.aluno.DadosListagemAluno;
import br.com.hartzescola.domain.usuario.Usuario;
import br.com.hartzescola.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        Usuario usuario = new Usuario(dados);
        Aluno aluno = new Aluno(dados);

        usuario.setAluno(aluno);
        aluno.setUsuario(usuario);

        alunoRepository.save(aluno);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAluno>> listar (@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = alunoRepository.findAll(paginacao).stream()
                .filter(aluno -> aluno.getUsuario().isAtivo())
                .map(DadosListagemAluno::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(page, paginacao, page.size()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        var aluno = alunoRepository.getReferenceById(dados.id());
        aluno.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();

        return ResponseEntity.noContent().build();
    }

}

