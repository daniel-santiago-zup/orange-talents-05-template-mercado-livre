package br.com.zup.zupacademy.daniel.mercadolivre.produto;

import br.com.zup.zupacademy.daniel.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.common.UploaderDeImagensFake;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto.FotoProduto;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto.FotoProdutoRequest;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.CaracteristicaProdutoUnicaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (binder.getTarget() != null
                && binder.getTarget().getClass() != null
                && caracteristicaProdutoUnicaValidator.supports(binder.getTarget().getClass()) ) {
            binder.addValidators(caracteristicaProdutoUnicaValidator);
        }
    }

    @PostMapping
    public ResponseEntity<Object> cadastraProduto(@RequestBody @Validated ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuarioLogado) {
        if (produtoRepository.existsByAnunciante(usuarioLogado)) {
            return ResponseEntity.badRequest().body("É permitido apenas um anúncio por usuário!");
        }
        produtoRepository.save(produtoRequest.converte(categoriaRepository,usuarioLogado));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/foto")
    @Transactional
    public ResponseEntity<String> recebeFotoDoProduto(@PathVariable Long id,@Valid FotoProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Produto produto = produtoOptional.get();
        if (!produto.ehAnunciante(usuarioLogado)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("usuário logado não é o anunciante do produto");
        }
        Set<FotoProduto> fotoProdutos = request.converte(new UploaderDeImagensFake());
        List<String> urlsFotoProduto = fotoProdutos.stream().map(FotoProduto::getUrlFoto).collect(Collectors.toList());
        produto.adicionaFotosProduto(fotoProdutos);
        produtoRepository.save(produto);
        return ResponseEntity.ok(urlsFotoProduto.toString());
    }
}
