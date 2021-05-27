package br.com.zup.zupacademy.daniel.mercadolivre.Produto;

import br.com.zup.zupacademy.daniel.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.CaracteristicaProdutoUnicaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CaracteristicaProdutoUnicaValidator caracteristicaProdutoUnicaValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(caracteristicaProdutoUnicaValidator);
    }

    @PostMapping
    public ResponseEntity<Object> cadastraProduto(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuarioLogado) {
        if (produtoRepository.existsByAnunciante(usuarioLogado)) {
            return ResponseEntity.badRequest().body("É permitido apenas um anúncio por usuário!");
        }
        produtoRepository.save(produtoRequest.converte(categoriaRepository,usuarioLogado));
        return ResponseEntity.ok().build();
    }
}
