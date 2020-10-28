package br.com.zup.braz.rui.proposta.validator;

import br.com.zup.braz.rui.proposta.annotation.DocumentoUnico;
import br.com.zup.braz.rui.proposta.configuration.error.ApiErroException;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DocumentoUnicoValidator implements ConstraintValidator<DocumentoUnico, String> {

    private String campo;
    private Class<?> aClass;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void initialize(DocumentoUnico documentoUnico) {
        campo = documentoUnico.fieldName();
        aClass = documentoUnico.domainClass();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {
        if (valor == null) return true;

        Query customQuery = entityManager.createQuery("select 1 from " + aClass.getName() + " where " + campo + " = :valor");
        customQuery.setParameter("valor", valor.replaceAll("[^0-9]", ""));
        List<?> resultadoConsulta = customQuery.getResultList();
        Assert.isTrue(resultadoConsulta.size() <= 1, "Ja existe um registro para o campo: " + campo + " = " + valor);
        if (!resultadoConsulta.isEmpty()) {
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta para este documento.");
        }

        return resultadoConsulta.isEmpty();
    }
}
