// package br.com.hartzescola.infra.security;

// import java.io.IOException;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import br.com.hartzescola.domain.aluno.AlunoRepository;

// @Component
// public class SecurityFilterAluno extends OncePerRequestFilter {

//     @Autowired
//     private TokenService tokenService;

//     @Autowired
//     private AlunoRepository alunoRepository;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//             HttpServletResponse response, FilterChain filterChain)
//             throws IOException, ServletException {
//         var tokenJWT = recuperarToken(request);

//         if (tokenJWT != null) {
//             var subject = tokenService.getSubject(tokenJWT);
//             var usuario = alunoRepository.findAlunoByEmail(subject);

//             var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
//                     usuario.getAuthorities());

//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }

//         filterChain.doFilter(request, response);
//     }

//     private String recuperarToken(HttpServletRequest request) {
//         var authorizationHeader = request.getHeader("Authorization");
//         if (authorizationHeader != null) {
//             return authorizationHeader.replace("Bearer ", "");
//         }

//         return null;
//     }

// }
