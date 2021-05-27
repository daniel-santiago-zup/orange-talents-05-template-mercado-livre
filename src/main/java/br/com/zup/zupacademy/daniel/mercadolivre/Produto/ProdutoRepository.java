package br.com.zup.zupacademy.daniel.mercadolivre.Produto;

import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto,Long> {
    boolean existsByAnunciante(Usuario anunciante);
}
