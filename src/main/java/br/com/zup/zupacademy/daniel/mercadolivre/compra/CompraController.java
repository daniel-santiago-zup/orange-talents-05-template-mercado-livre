package br.com.zup.zupacademy.daniel.mercadolivre.compra;

import br.com.zup.zupacademy.daniel.mercadolivre.common.Emails;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.ProdutoRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.usuario.Usuario;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.DeveTerProdutoEmEstoqueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CompraController {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    DeveTerProdutoEmEstoqueValidator deveTerProdutoEmEstoqueValidator;
    @Autowired
    Emails emails;

    @InitBinder
    public void initBinders(WebDataBinder binder) {
        binder.addValidators(deveTerProdutoEmEstoqueValidator);
    }

    @PostMapping("/compra")
    @Transactional
    public ResponseEntity<?> cadastraCompra(@RequestBody @Valid CompraRequest request,
                                       @AuthenticationPrincipal Usuario usuario) {
        Compra compra = compraRepository.save(request.converte(produtoRepository,usuario));
        emails.novaCompra(compra);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(compra.processaCompra("www.google.com")));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
