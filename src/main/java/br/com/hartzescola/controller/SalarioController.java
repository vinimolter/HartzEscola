package br.com.hartzescola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hartzescola.domain.funcionario.DadosDetalhamentoFuncionario;
import br.com.hartzescola.domain.funcionario.FuncionarioRepository;
import br.com.hartzescola.domain.funcionario.salario.DadosAumentoSalario;
import br.com.hartzescola.domain.funcionario.salario.DadosAlterarContaSalario;

@RestController
public class SalarioController {

    @Autowired
    private FuncionarioRepository repository;

    @PutMapping
    @RequestMapping("aumento-de-salario")
    @Transactional
    public ResponseEntity aumentarSalario(@RequestBody DadosAumentoSalario dados){
        var funcionario = repository.getReferenceById(dados.id());
        funcionario.atualizarSalario(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @PutMapping
    @RequestMapping("alterar-dados-conta-salario")
    @Transactional
    public ResponseEntity alterarDadosDaContaSalario(@RequestBody DadosAlterarContaSalario dados){
        var funcionario = repository.getReferenceById(dados.id());
        funcionario.atualizarDadosDaConta(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }
    
}
