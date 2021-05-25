package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    @Autowired
    private EntityManager entityManager;
    private Class<?> entidade;
    private String nomeCampo;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        entidade = constraintAnnotation.entidade();
        nomeCampo = constraintAnnotation.nomeCampo();
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        List<?> lista = entityManager.createQuery("select c from "+ entidade.getSimpleName() +" c where c."+nomeCampo+"= :pCampo")
                .setParameter("pCampo",s)
                .getResultList();

        return lista.isEmpty();
    }
}
