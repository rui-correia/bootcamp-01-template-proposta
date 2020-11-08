package br.com.zup.braz.rui.proposta.response;

public class PropostaResponse {

    private String id;
    private String documento;


    public PropostaResponse(String id, String documento) {
        this.id = id;
        this.documento = documento;
    }

    public String getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }
}
