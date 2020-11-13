package br.com.zup.braz.rui.proposta.response;

import javax.validation.constraints.NotBlank;

public class BloquearCartaoResponse {

    @NotBlank
    private String resultado;

    @Deprecated
    public BloquearCartaoResponse() {
    }


    public BloquearCartaoResponse(@NotBlank String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
