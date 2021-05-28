package br.com.zup.zupacademy.daniel.mercadolivre.produto.caracteristicaProduto;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class CaracteristicaProduto {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @Deprecated
    public CaracteristicaProduto() {}

    public CaracteristicaProduto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}