package br.com.hartzescola.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hartzescola.domain.aluno.Aluno;
import br.com.hartzescola.domain.autenticacao.DadosAutenticacao;
import br.com.hartzescola.domain.funcionario.Funcionario;
import br.com.hartzescola.infra.security.DadosTokenJWT;
import br.com.hartzescola.infra.security.TokenServiceAluno;

@RestController

public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenServiceAluno tokenService;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity efetuarLogin (@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(token);

        if(authentication.getPrincipal() instanceof Funcionario){
            var tokenJWT = tokenService.gerarToken((Funcionario) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        }
        if(authentication.getPrincipal() instanceof Aluno){
            var tokenJWT = tokenService.gerarTokenAluno((Aluno) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        }
        else{
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
