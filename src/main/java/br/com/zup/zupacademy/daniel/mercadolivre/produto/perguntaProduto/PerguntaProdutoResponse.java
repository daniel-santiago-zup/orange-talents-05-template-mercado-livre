package br.com.zup.zupacademy.daniel.mercadolivre.produto.perguntaProduto;

public class PerguntaProdutoResponse {
    private String titulo;

    public PerguntaProdutoResponse(PerguntaProduto perguntaProduto) {
        this.titulo = perguntaProduto.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
