package br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoProdutoRequest {
    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;

    public OpiniaoProdutoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoProduto coverte(Produto produto, Usuario usuarioLogado) {
        return new OpiniaoProduto(this.nota,this.titulo,this.descricao,usuarioLogado,produto);
    }
}
