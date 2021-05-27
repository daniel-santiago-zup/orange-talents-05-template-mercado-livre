package br.com.zup.zupacademy.daniel.mercadolivre.caracteristicaProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.Produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @ManyToOne
    private Produto produto;

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CaracteristicaProduto that = (CaracteristicaProduto) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        return produto != null ? produto.equals(that.produto) : that.produto == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        return result;
    }
}