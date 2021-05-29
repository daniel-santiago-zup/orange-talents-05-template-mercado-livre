package br.com.zup.zupacademy.daniel.mercadolivre.produto.perguntaProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PerguntaProdutoRequest {
    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaProdutoRequest(String titulo) {
        this.titulo = titulo;
    }

    public PerguntaProduto converte(Produto produto, Usuario usuario) {
        return new PerguntaProduto(this.titulo,usuario,produto);
    }
}
