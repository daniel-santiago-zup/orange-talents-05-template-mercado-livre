package br.com.zup.zupacademy.daniel.mercadolivre.caracteristicaProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.Produto.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaProdutoRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;


    public CaracteristicaProdutoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto converte(@NotNull @Valid Produto produto) {
        return new CaracteristicaProduto(this.nome,this.descricao,produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CaracteristicaProdutoRequest that = (CaracteristicaProdutoRequest) o;

        return getNome().equals(that.getNome());
    }

    @Override
    public int hashCode() {
        return getNome().hashCode();
    }

}
