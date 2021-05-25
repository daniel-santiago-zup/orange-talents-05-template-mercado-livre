package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValorUnicoValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {
    String message() default "Valor deve ser único!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> entidade();
    String nomeCampo();
}
