package br.com.hartzescola.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecutiryConfigurations {

    @Autowired
    private SecurityFilter SecurityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(SecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(requests -> requests
                        .antMatchers(HttpMethod.POST, "/login").permitAll()
                        .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        //PERMISSÕES DE CADA ENDPOINT
                        .antMatchers(HttpMethod.POST,"/alunos").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.GET,"/alunos").hasAnyRole("DIRETOR", "COORDENADOR", "PROFESSOR")
                        .antMatchers(HttpMethod.PUT,"/alunos").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.DELETE,"/alunos").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.POST,"/cursos").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.GET,"/cursos").hasAnyRole("DIRETOR", "COORDENADOR", "PROFESSOR")
                        .antMatchers(HttpMethod.POST,"/funcionarios").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.GET,"/funcionarios").hasAnyRole("DIRETOR", "COORDENADOR", "PROFESSOR")
                        .antMatchers(HttpMethod.PUT,"/funcionarios").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.DELETE,"/funcionarios").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.POST,"/matriculas").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.GET,"/matriculadoscurso").hasAnyRole("DIRETOR", "COORDENADOR", "PROFESSOR")
                        .antMatchers(HttpMethod.GET,"/matriculasaluno").hasAnyRole("DIRETOR", "COORDENADOR", "PROFESSOR")
                        .antMatchers(HttpMethod.GET,"/meuscursos").hasAnyRole("ALUNO")
                        .antMatchers(HttpMethod.POST,"/professor").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.GET,"/professor").hasAnyRole("DIRETOR", "COORDENADOR", "PROFESSOR")
                        .antMatchers(HttpMethod.PUT,"/professor").hasAnyRole("DIRETOR", "COORDENADOR")
                        .antMatchers(HttpMethod.PUT,"/aumento-de-salario").hasAnyRole("DIRETOR")
                        .antMatchers(HttpMethod.PUT,"/alterar-dados-conta-salario").hasAnyRole("DIRETOR")
                        .anyRequest().authenticated())
                .build(); 
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}