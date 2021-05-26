package br.com.zup.zupacademy.daniel.mercadolivre.configuration.security;

import br.com.zup.zupacademy.daniel.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // Configurações de Autenticação (controlede acesso, logind etc)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Configurações de Autorização (diz quem pode acessar cada url, perfis de aceso etc)
    @Override
        protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

        .antMatchers(HttpMethod.POST,"/usuario")
        .permitAll()

        .antMatchers(HttpMethod.POST,"/auth")
        .permitAll()

        .anyRequest()
        .authenticated()

        .and().csrf().disable()

        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and().addFilterBefore(new AutenticacaoTokenFilter(usuarioRepository, tokenService), UsernamePasswordAuthenticationFilter.class);
    }


    // Configurações de recursos estáticos (javascript, css, imagens etc)
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
