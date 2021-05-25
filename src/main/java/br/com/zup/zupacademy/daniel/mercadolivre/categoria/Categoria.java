package br.com.zup.zupacademy.daniel.mercadolivre.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "nome")})
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne(optional = true)
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
        this.categoriaMae = null;
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }
}
