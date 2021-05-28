package br.com.zup.zupacademy.daniel.mercadolivre.produto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.caracteristicaProduto.CaracteristicaProduto;
import br.com.zup.zupacademy.daniel.mercadolivre.categoria.Categoria;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto.FotoProduto;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

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
    @Size(min = 1)
    @ElementCollection(targetClass = FotoProduto.class)
    private Set<FotoProduto> fotosProduto;
    @Size(min=3)
    @ElementCollection(targetClass = CaracteristicaProduto.class)
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
                   Set<CaracteristicaProduto> caracteristicas,
                   String descricao,
                   Categoria categoria,
                   Usuario anunciante) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.anunciante = anunciante;
    }

    public void adicionaFotosProduto(Collection<FotoProduto> fotoProduto) {
        this.fotosProduto.addAll(fotoProduto);
    }

    public boolean ehAnunciante(Usuario possivelAnunciante) {
        return this.anunciante.equals(possivelAnunciante);
    }
}