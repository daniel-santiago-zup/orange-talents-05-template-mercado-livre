package br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto;

public class OpiniaoProdutoResponse {
    private Integer nota;
    private String titulo;
    private String descricao;

    public OpiniaoProdutoResponse(OpiniaoProduto opiniaoProduto) {
        this.nota = opiniaoProduto.getNota();
        this.titulo = opiniaoProduto.getTitulo();
        this.descricao = opiniaoProduto.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
