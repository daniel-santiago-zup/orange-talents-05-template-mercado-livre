package br.com.zup.zupacademy.daniel.mercadolivre.usuario;

import br.com.zup.zupacademy.daniel.mercadolivre.validadores.EncodadoComBCrypt;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.EncodadoComBCryptValidator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @PastOrPresent
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Length(min = 6)
    @EncodadoComBCrypt
    private String senha;

    @Deprecated
    public Usuario() {}

    public Usuario(String login, String senha) {
        Assert.isTrue(!new EncodadoComBCryptValidator().isValid(senha,null),"A senha fornecida não deve ser encodada previamente com BCrypt");
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (getId() != null ? !getId().equals(usuario.getId()) : usuario.getId() != null) return false;
        if (instanteCadastro != null ? !instanteCadastro.equals(usuario.instanteCadastro) : usuario.instanteCadastro != null)
            return false;
        if (login != null ? !login.equals(usuario.login) : usuario.login != null) return false;
        return senha != null ? senha.equals(usuario.senha) : usuario.senha == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (instanteCadastro != null ? instanteCadastro.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        return result;
    }
}
