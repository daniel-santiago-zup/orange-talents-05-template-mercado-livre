package br.com.zup.zupacademy.daniel.mercadolivre.usuario;

import br.com.zup.zupacademy.daniel.mercadolivre.validadores.ValorUnico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {
    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converte() {
        return new Usuario(this.login, this.senha);
    }
}
