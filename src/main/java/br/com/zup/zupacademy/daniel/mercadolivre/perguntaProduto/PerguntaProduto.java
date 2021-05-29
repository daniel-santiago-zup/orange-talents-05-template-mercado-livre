package br.com.zup.zupacademy.daniel.mercadolivre.perguntaProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class PerguntaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    private LocalDateTime instanteCriacao = LocalDateTime.now();
    @NotNull
    @ManyToOne
    private Usuario autor;
    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public PerguntaProduto() {}

    public PerguntaProduto(String titulo, Usuario autor, Produto produto) {
        this.titulo = titulo;
        this.autor = autor;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }
}
