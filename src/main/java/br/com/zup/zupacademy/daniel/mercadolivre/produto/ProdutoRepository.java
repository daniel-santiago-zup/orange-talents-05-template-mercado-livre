package br.com.zup.zupacademy.daniel.mercadolivre.produto;

import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto,Long> {
    boolean existsByAnunciante(Usuario anunciante);
}
