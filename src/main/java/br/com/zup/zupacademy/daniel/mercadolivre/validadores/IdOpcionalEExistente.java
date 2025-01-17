package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdOpcionalEExistenteValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdOpcionalEExistente {
    String message() default "Valor deve existir no banco de dados!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> classeEntidade();
    String nomeCampoId() default "id";
    Class<?> tipoCampoId() default Long.class;
}
