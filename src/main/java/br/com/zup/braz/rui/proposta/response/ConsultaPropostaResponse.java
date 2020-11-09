package br.com.zup.braz.rui.proposta.response;

import br.com.zup.braz.rui.proposta.domain.StatusProposta;

public class ConsultaPropostaResponse {

    private String id;
    private String documento;
    private StatusProposta statusProposta;

    public ConsultaPropostaResponse(String id, String documento, StatusProposta statusProposta) {
        this.id = id;
        this.documento = documento;
        this.statusProposta = statusProposta;
    }

    public String getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
