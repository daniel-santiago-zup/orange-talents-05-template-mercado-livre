package br.com.zup.zupacademy.daniel.mercadolivre.categoria;

public class CategoriaResponse {
    private String nome;
    private CategoriaResponse categoriaMae;

    public CategoriaResponse(Categoria categoria) {
        this.nome = categoria.getNome();
        if (categoria.getCategoriaMae() != null) {
            this.categoriaMae = new CategoriaResponse(categoria.getCategoriaMae());
        }
    }

    public String getNome() {
        return nome;
    }

    public CategoriaResponse getCategoriaMae() {
        return categoriaMae;
    }
}