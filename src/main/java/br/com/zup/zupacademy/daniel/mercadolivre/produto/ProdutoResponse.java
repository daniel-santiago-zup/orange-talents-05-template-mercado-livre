package br.com.zup.zupacademy.daniel.mercadolivre.produto;

import br.com.zup.zupacademy.daniel.mercadolivre.categoria.CategoriaResponse;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.caracteristicaProduto.CaracteristicaProdutoResponse;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto.OpiniaoProdutoRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto.OpiniaoProdutoResponse;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto.OpinioesProduto;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.perguntaProduto.PerguntaProdutoRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.perguntaProduto.PerguntaProdutoResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoResponse {
    private List<String> linksFotos;
    private String nome;
    private BigDecimal valor;
    private CategoriaResponse categoria;
    private Integer quantidade;
    private Set<CaracteristicaProdutoResponse> caracteristicas;
    private String descricao;
    private Double mediaNotas;
    private Integer numeroDeNotas;
    private Set<OpiniaoProdutoResponse> opinioes;
    private Set<PerguntaProdutoResponse> perguntas;

    public ProdutoResponse(Produto produto,
                           OpiniaoProdutoRepository opiniaoProdutoRepository,
                           PerguntaProdutoRepository perguntaProdutoRepository) {

        OpinioesProduto opinioesProduto = new OpinioesProduto(opiniaoProdutoRepository.findByProduto(produto));

        this.linksFotos = produto.linksFotos();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.categoria =  new CategoriaResponse(produto.getCategoria());
        this.quantidade = produto.getQuantidade();
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaProdutoResponse::new).collect(Collectors.toSet());
        this.descricao = produto.getDescricao();
        this.mediaNotas = opinioesProduto.media();
        this.numeroDeNotas = opinioesProduto.getNumeroDeNotas();
        this.opinioes = opinioesProduto.getOpinioesResponse();
        this.perguntas = perguntaProdutoRepository.findByProduto(produto).stream().map(PerguntaProdutoResponse::new).collect(Collectors.toSet());
    }

    public List<String> getLinksFotos() {
        return linksFotos;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getNumeroDeNotas() {
        return numeroDeNotas;
    }

    public Set<OpiniaoProdutoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaProdutoResponse> getPerguntas() {
        return perguntas;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }
}
