package br.com.zup.braz.rui.proposta.request;

public class AnalisePropostaRequest {
    public String documento;
    public String nome;
    public String idProposta;

    public AnalisePropostaRequest(String documento, String nome, Long id) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = id.toString();
    }
}
