package br.com.zup.zupacademy.daniel.mercadolivre.produto.opiniaoProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.ProdutoRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class OpiniaoProdutoController {

    @Autowired
    OpiniaoProdutoRepository opiniaoProdutoRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/produto/{id}/opiniao")
    public ResponseEntity<?> cadastraOpiniaoProduto(@PathVariable Long id, @RequestBody @Valid OpiniaoProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de produto não encontrado");
        }
        Produto produto = optionalProduto.get();
        opiniaoProdutoRepository.save(request.coverte(produto,usuarioLogado));
        return ResponseEntity.ok().build();
    }
}
