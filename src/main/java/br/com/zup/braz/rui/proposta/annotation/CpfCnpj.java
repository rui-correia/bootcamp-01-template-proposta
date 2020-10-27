package br.com.zup.braz.rui.proposta.annotation;

import br.com.zup.braz.rui.proposta.validator.CpfCnpjValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CpfCnpjValidator.class)
public @interface CpfCnpj {

    String message() default "Documento inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}
