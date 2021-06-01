package br.com.zup.zupacademy.daniel.mercadolivre.compra;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.StatusTransacao;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.Transacao;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

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
    @OneToMany(mappedBy = "compra",cascade = CascadeType.PERSIST)
    @Size(max = 2)
    private Set<Transacao> transacoes;

    @Deprecated
    public Compra() {}

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

    public String geraLinkDePagamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayDePagamento.geraLinkDePagamento(this.id,uriComponentsBuilder);
    }

    public StatusTransacao processaStatusDePagamento(String statusFornecido) {
        return this.gatewayDePagamento.processaStatusDePagamento(statusFornecido);
    }

    public String nomeDoProdutoComprado() {
        return this.produto.getNome();
    }

    public String emailDoComprador() {
        return this.comprador.getUsername();
    }

    public String emailDoAnunciante() {
        return this.produto.emailDoAnunciante();
    }

    public Long getId() {
        return id;
    }

    public Long getIdVendedor() {
        return this.produto.getIdDoAnunciante();
    }

    public Long getIdComprador() {
        return comprador.getId();
    }

    public void adicionaTransacao(Transacao transacao) {
        if (foiProcessadaComSucesso()) {
            throw new IllegalArgumentException("Ja existe uma transação com status de SUCESSO");
        }
        this.transacoes.add(transacao);
    }

    public boolean foiProcessadaComSucesso() {
        return this.transacoes.stream().anyMatch(t -> t.getStatusTrasacao().equals(StatusTransacao.SUCESSO));
    }
}