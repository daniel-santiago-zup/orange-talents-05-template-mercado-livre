package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PossuiContentTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PossuiContentType {
    String message() default "Content-type fornecido não aceito";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String comecaCom() default "";
}
