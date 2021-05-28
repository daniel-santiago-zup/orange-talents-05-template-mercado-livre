package br.com.zup.zupacademy.daniel.mercadolivre.produto.caracteristicaProduto;

import javax.validation.constraints.NotBlank;

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

    public CaracteristicaProduto converte() {
        return new CaracteristicaProduto(this.nome,this.descricao);
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
