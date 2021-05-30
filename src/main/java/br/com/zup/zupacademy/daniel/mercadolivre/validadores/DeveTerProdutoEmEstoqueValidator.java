package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.CompraRequest;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.Produto;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DeveTerProdutoEmEstoqueValidator implements Validator {
    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CompraRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        CompraRequest compraRequest = (CompraRequest) o;
        Produto produto = produtoRepository.findById(compraRequest.getIdProduto()).orElseThrow(() -> new IllegalArgumentException("Produto buscado não existe"));
        if (compraRequest.getQuantidade() > produto.getQuantidade()) {
            errors.rejectValue("quantidade","DeveTerProdutoEmEstoqueValidator","Quantidade fornecida excede o estoque do produto");
        }
    }
}
