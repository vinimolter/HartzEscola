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

import br.com.hartzescola.domain.funcionario.DadosAtualizacaoFuncionario;
import br.com.hartzescola.domain.funcionario.DadosCadastroFuncionario;
import br.com.hartzescola.domain.funcionario.DadosDetalhamentoFuncionario;
import br.com.hartzescola.domain.funcionario.DadosListagemFuncionario;
import br.com.hartzescola.domain.funcionario.Funcionario;
import br.com.hartzescola.domain.funcionario.FuncionarioRepository;
import br.com.hartzescola.domain.usuario.Usuario;
import br.com.hartzescola.domain.usuario.UsuarioRepository;


@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dados);
        var funcionario = new Funcionario(dados);

        usuario.setFuncionario(funcionario);
        funcionario.setUser(usuario);
        
        funcionarioRepository.save(funcionario);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemFuncionario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = funcionarioRepository.findAll(paginacao).stream()
                .filter(funcionario -> funcionario.getUsuario().isAtivo())
                .map(DadosListagemFuncionario::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PageImpl<>(page, paginacao, page.size()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados){
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();

        return ResponseEntity.noContent().build();
    }

}
