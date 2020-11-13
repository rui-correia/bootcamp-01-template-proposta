package br.com.zup.braz.rui.proposta.response;

public class BloqueioResponse {

    String id;

    @Deprecated
    public BloqueioResponse() {
    }

    public BloqueioResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
