package br.com.zup.braz.rui.proposta.response;

import br.com.zup.braz.rui.proposta.domain.StatusAnalise;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalisePropostaResponse {

    @JsonProperty("resultadoSolicitacao")
    private StatusAnalise statusAnalise;

    public StatusAnalise getStatusAnalise() {
        return statusAnalise;
    }

    public void setStatusAnalise(StatusAnalise statusAnalise) {
        this.statusAnalise = statusAnalise;
    }
}
