package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IdOpcionalEExistenteValidator implements ConstraintValidator<IdOpcionalEExistente, Object> {

    @PersistenceContext
    EntityManager entityManager;

    String nomeCapoId;
    Class<?> classeEntidade;
    Class<?> tipoCampoId;

    @Override
    public void initialize(IdOpcionalEExistente constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.nomeCapoId = constraintAnnotation.nomeCampoId();
        this.classeEntidade = constraintAnnotation.classeEntidade();
        this.tipoCampoId = constraintAnnotation.tipoCampoId();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o != null) {
            List<?> listaEntidades = entityManager.createQuery("select c from "+this.classeEntidade.getName()+" c where c."+this.nomeCapoId+"=:pValorId")
                    .setParameter("pValorId",this.tipoCampoId.cast(o))
                    .getResultList();
            return !listaEntidades.isEmpty();
        }
        return true;
    }
}