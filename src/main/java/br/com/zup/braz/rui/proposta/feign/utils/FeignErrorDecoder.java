package br.com.zup.braz.rui.proposta.feign.utils;

import br.com.zup.braz.rui.proposta.exception.ApiErroException;
import br.com.zup.braz.rui.proposta.exception.DadosImprocessaveisException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {
            case 422:
                return new DadosImprocessaveisException(HttpStatus.UNPROCESSABLE_ENTITY, "Analise recusou a proposta.");

            case 404:
                return new ApiErroException(HttpStatus.NOT_FOUND, response.reason());

            default:
                return new ApiErroException(HttpStatus.valueOf(response.status()), "Ocorreu um erro: " + response.reason() + response.toString());
        }
    }
}
