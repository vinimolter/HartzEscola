package br.com.hartzescola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hartzescola.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/hello")
public class HelloController {

    // // @Autowired
    // // private UsuarioRepository usuarioRepository;

    // // @GetMapping("/{id}")
    public void olaMundo() {
        System.out.println("TESTEEEE");
    }
    // // long testeVar = 17;
    // // System.out.println("TESTE: " + usuarioRepository.findById(testeVar));
    // }

}
