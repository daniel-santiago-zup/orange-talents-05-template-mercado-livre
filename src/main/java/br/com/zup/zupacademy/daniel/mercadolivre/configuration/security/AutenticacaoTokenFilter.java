package br.com.zup.zupacademy.daniel.mercadolivre.configuration.security;

import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    public AutenticacaoTokenFilter(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperaToken(httpServletRequest);

        boolean valido = tokenService.validaToken(token);

        if (valido) {
            autenticaCliente(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    public void autenticaCliente(String token) {
        Usuario usuario = usuarioRepository.findById(tokenService.getIdUsuario(token)).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recuperaToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return  null;
        }

        return token.substring(7, token.length());
    }
}
