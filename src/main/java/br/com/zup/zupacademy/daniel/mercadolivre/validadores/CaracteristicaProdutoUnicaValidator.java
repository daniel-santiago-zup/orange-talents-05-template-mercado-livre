package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import br.com.zup.zupacademy.daniel.mercadolivre.Produto.ProdutoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CaracteristicaProdutoUnicaValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(ProdutoRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        ProdutoRequest produtoRequest = (ProdutoRequest) o;
        if (produtoRequest.possuiCaracteristicasRepetidas()) {
            errors.rejectValue("caracteristicas", "CaracteristicaProdutoUnicaValidator","O produto possui características repetidas!");
        }
    }
}
