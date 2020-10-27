package br.com.zup.braz.rui.proposta.validator;

import br.com.zup.braz.rui.proposta.annotation.CpfCnpj;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

    private CPFValidator cpfValidator;
    private CNPJValidator cnpjValidator;

    @Override
    public void initialize(CpfCnpj constraintAnnotation) {
        cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

    }

    @Override
    public boolean isValid(String documento, ConstraintValidatorContext constraintValidatorContext) {
        return cpfValidator.isValid(documento, constraintValidatorContext) || cnpjValidator.isValid(documento, constraintValidatorContext);
    }
}
