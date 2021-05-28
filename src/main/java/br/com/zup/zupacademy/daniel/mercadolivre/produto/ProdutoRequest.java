package br.com.zup.zupacademy.daniel.mercadolivre.produto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.caracteristicaProduto.CaracteristicaProdutoRequest;
import br.com.zup.zupacademy.daniel.mercadolivre.categoria.Categoria;
import br.com.zup.zupacademy.daniel.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.IdExistente;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @Positive
    @NotNull
    private BigDecimal valor;
    @Positive
    @NotNull
    private Integer quantidade;
    @Size(min=3)
    @Valid
    @NotEmpty
    private List<CaracteristicaProdutoRequest> caracteristicas;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @IdExistente(classeEntidade = Categoria.class)
    private Long idCategoria;

    public List<CaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public ProdutoRequest(String nome,
                          BigDecimal valor,
                          Integer quantidade,
                          List<CaracteristicaProdutoRequest> caracteristicas,
                          String descricao,
                          Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Produto converte(CategoriaRepository categoriaRepository, Usuario anuncianteLogado) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(this.idCategoria);
        Assert.isTrue(categoriaOptional.isPresent(), "Não foi encontrado uma categoria com esse Id");

        return new Produto(this.nome,
                this.valor,
                this.quantidade,
                this.caracteristicas.stream().map(CaracteristicaProdutoRequest::converte).collect(Collectors.toSet()),
                this.descricao,
                categoriaOptional.get(),
                anuncianteLogado);
    }

    public boolean possuiCaracteristicasRepetidas() {
        for (CaracteristicaProdutoRequest caracteristica : this.caracteristicas) {
            if (this.caracteristicas.stream().filter(caracteristica::equals).count() > 1) {
                return true;
            };
        }

        return false;
    }
}
