package br.com.zup.braz.rui.proposta.annotation;

import br.com.zup.braz.rui.proposta.validator.DocumentoUnicoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DocumentoUnicoValidator.class)
public @interface DocumentoUnico {

    String message() default "Já cadastrado no banco.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}
