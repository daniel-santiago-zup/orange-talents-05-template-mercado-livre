package br.com.zup.zupacademy.daniel.mercadolivre.categoria;

import br.com.zup.zupacademy.daniel.mercadolivre.validadores.IdOpcionalEExistente;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.ValorUnico;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank
    @ValorUnico(entidade = Categoria.class, nomeCampo = "nome")
    private String nome;
    @IdOpcionalEExistente(classeEntidade = Categoria.class)
    private Long idCategoriaMae;

    public CategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converte(CategoriaRepository categoriaRepository) {
        Categoria categoria;

        // Se tiver um idCategoriaMae a busca no banco de dados e setta na categoria retornada
        if (idCategoriaMae == null) {
            categoria = new Categoria(this.nome);
        } else {
            Optional<Categoria> categoriaMaeOptional = categoriaRepository.findById(idCategoriaMae);
            Assert.isTrue(categoriaMaeOptional.isPresent(), "id da categoria não existe no banco de dados");
            categoria = new Categoria(this.nome, categoriaMaeOptional.get());
        }

        return categoria;
    }
}
