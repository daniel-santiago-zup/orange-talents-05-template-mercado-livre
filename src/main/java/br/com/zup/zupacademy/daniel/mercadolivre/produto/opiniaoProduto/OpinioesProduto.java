package br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto;

import java.util.Set;
import java.util.stream.Collectors;

public class OpinioesProduto {
    private Set<OpiniaoProduto> opinioesProduto;

    public OpinioesProduto(Set<OpiniaoProduto> opinioesProduto) {
        this.opinioesProduto = opinioesProduto;
    }

    public Set<OpiniaoProdutoResponse> getOpinioesResponse() {
        return opinioesProduto.stream().map(OpiniaoProdutoResponse::new).collect(Collectors.toSet());
    }

    public Double media() {
        return opinioesProduto.stream().mapToDouble(OpiniaoProduto::getNota).average().orElse(0.0);
    }

    public Integer getNumeroDeNotas() {
        return opinioesProduto.size();
    }
}
