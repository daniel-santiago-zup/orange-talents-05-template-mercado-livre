package br.com.zup.zupacademy.daniel.mercadolivre.compra;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;
    @Positive
    private Integer quantidade;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayDePagamento gatewayDePagamento;
    @NotNull
    @ManyToOne
    private Produto produto;
    @NotNull
    @ManyToOne
    private Usuario comprador;
    @Positive
    private BigDecimal valorUnidade;
    @Positive
    private BigDecimal valorTotal;

    public Compra(Integer quantidade,
                  GatewayDePagamento gatewayDePagamento,
                  Produto produto,
                  Usuario comprador) {
        this.statusCompra = StatusCompra.INICIADA;
        this.quantidade = quantidade;
        this.gatewayDePagamento = gatewayDePagamento;
        this.produto = produto;
        this.comprador = comprador;
        this.valorUnidade = produto.getValor();
        this.valorTotal = valorUnidade.multiply(BigDecimal.valueOf(quantidade));
    }

    public String processaCompra(String urlDeRetonoAposPagamento) {
        return this.gatewayDePagamento.processaPagamento(this.id,urlDeRetonoAposPagamento);
    }

    public String nomeDoProdutoComprado() {
        return this.produto.getNome();
    }
}