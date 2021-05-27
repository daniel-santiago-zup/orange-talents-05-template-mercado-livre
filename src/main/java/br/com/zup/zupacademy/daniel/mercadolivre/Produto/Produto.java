package br.com.zup.zupacademy.daniel.mercadolivre.Produto;

import br.com.zup.zupacademy.daniel.mercadolivre.caracteristicaProduto.CaracteristicaProduto;
import br.com.zup.zupacademy.daniel.mercadolivre.caracteristicaProduto.CaracteristicaProdutoRequest;
import br.com.zup.zupacademy.daniel.mercadolivre.categoria.Categoria;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Positive
    @NotNull
    private BigDecimal valor;
    @Positive
    @NotNull
    private Integer quantidade;
    @Size(min=3)
    @NotEmpty
    @OneToMany(mappedBy = "produto",cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ManyToOne
    @Valid
    private Categoria categoria;
    @NotNull
    @ManyToOne
    private Usuario anunciante;
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @Deprecated
    public Produto() {}


    public Produto(String nome,
                   BigDecimal valor,
                   Integer quantidade,
                   Collection<CaracteristicaProdutoRequest> caracteristicas,
                   String descricao,
                   Categoria categoria,
                   Usuario anunciante) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.converte(this))
                .collect(Collectors.toSet());
        this.descricao = descricao;
        this.categoria = categoria;
        this.anunciante = anunciante;
    }
}
