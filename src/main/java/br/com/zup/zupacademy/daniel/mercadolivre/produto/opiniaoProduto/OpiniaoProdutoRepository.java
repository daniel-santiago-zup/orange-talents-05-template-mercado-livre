package br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OpiniaoProdutoRepository extends CrudRepository<OpiniaoProduto,Long> {
    Set<OpiniaoProduto> findByProduto(Produto produto);
}
