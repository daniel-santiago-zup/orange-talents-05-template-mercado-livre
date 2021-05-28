package br.com.zup.zupacademy.daniel.mercadolivre.opiniaoProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class OpiniaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    @NotNull
    @ManyToOne
    private Usuario autor;
    @NotNull
    @OneToOne
    private Produto produto;

    @Deprecated
    public OpiniaoProduto() {}

    public OpiniaoProduto(Integer nota, String titulo, String descricao, Usuario autor, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.produto = produto;
    }
}