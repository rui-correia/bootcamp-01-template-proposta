package br.com.zup.braz.rui.proposta.request;

import javax.validation.constraints.NotBlank;

public class AnalisePropostaRequest {
    @NotBlank
    public String documento;
    @NotBlank
    public String nome;
    @NotBlank
    public String idProposta;

    public AnalisePropostaRequest(@NotBlank String documento, @NotBlank String nome, @NotBlank String id) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
