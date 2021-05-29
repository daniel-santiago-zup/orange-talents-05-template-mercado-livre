package br.com.zup.zupacademy.daniel.mercadolivre.perguntaProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.common.Emails;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.ProdutoRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    PerguntaProdutoRepository perguntaProdutoRepository;
    @Autowired
    Emails emails;

    @PostMapping("/produto/{id}/pergunta")
    public ResponseEntity<?> cadastraPergunta(@PathVariable Long id, @RequestBody @Valid PerguntaProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PerguntaProduto perguntaProduto = request.converte(produtoOptional.get(),usuarioLogado);
        perguntaProdutoRepository.save(perguntaProduto);
        emails.novaPergunta(perguntaProduto);
        return ResponseEntity.ok().build();
    }
}
