package br.com.zup.zupacademy.daniel.mercadolivre.produto.perguntaProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PerguntaProdutoRepository extends CrudRepository<PerguntaProduto,Long> {
    Set<PerguntaProduto> findByProduto(Produto produto);
}
