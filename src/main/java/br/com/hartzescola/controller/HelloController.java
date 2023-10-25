package br.com.hartzescola.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public void olaMundo(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            System.out.println("USUARIO VAZIO");
        }
        System.out.println("AUTORIDADE: " + userDetails.getAuthorities());
    }
}
