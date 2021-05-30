package br.com.zup.zupacademy.daniel.mercadolivre.compra;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.ProdutoRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.IdExistente;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {
    @NotNull
    @IdExistente(classeEntidade = Produto.class)
    private Long idProduto;
    @Positive
    private Integer quantidade;
    @NotNull
    private GatewayDePagamento gatewayDePagamento;

    public CompraRequest(Integer quantidade,
                         GatewayDePagamento gatewayDePagamento,
                         Long idProduto) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gatewayDePagamento = gatewayDePagamento;
    }

    public Compra converte(ProdutoRepository produtoRepository, Usuario comprador) {
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new IllegalArgumentException("Produto buscado não existe"));
        produto.abateNoEstoque(quantidade);
        return new Compra(quantidade, gatewayDePagamento,produto,comprador);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}


