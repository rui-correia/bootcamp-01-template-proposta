package br.com.zup.braz.rui.proposta.response;

public class NovaBiometriaResponse {

    private String id;
    private String biometria;

    public NovaBiometriaResponse(String id, String biometria){
        this.id = id;
        this.biometria = biometria;
    }

    public String getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }
}
