package br.com.zup.braz.rui.proposta.exception;

import org.springframework.http.HttpStatus;

public class DadosImprocessaveisException extends RuntimeException{

    private final HttpStatus httpStatus;

    private final String reason;

    public DadosImprocessaveisException(HttpStatus httpStatus, String reason){
        super(reason);
        this.httpStatus = httpStatus;
        this.reason = reason;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getReason() {
        return reason;
    }
}
