package br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class FotoProduto {
    @NotBlank
    private String nomeFoto;
    @NotBlank
    private String urlFoto;

    @Deprecated
    public FotoProduto() {}

    public FotoProduto(String nomeFoto, String urlFoto) {
        this.nomeFoto = nomeFoto;
        this.urlFoto = urlFoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FotoProduto that = (FotoProduto) o;

        if (!nomeFoto.equals(that.nomeFoto)) return false;
        return urlFoto.equals(that.urlFoto);
    }

    @Override
    public int hashCode() {
        int result = nomeFoto.hashCode();
        result = 31 * result + urlFoto.hashCode();
        return result;
    }

    public String getUrlFoto() {
        return urlFoto;
    }
}
