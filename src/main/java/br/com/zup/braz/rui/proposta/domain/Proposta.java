package br.com.zup.braz.rui.proposta.domain;

import br.com.zup.braz.rui.proposta.request.AnalisePropostaRequest;
import br.com.zup.braz.rui.proposta.request.SolicitaCartaoRequest;
import br.com.zup.braz.rui.proposta.response.ConsultaPropostaResponse;
import br.com.zup.braz.rui.proposta.response.PropostaResponse;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;



@Entity
@Table(name = "t_proposta")
public class Proposta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;
    private String numeroCartao;

    public Proposta(String s, String s1, String testeUm, String avenida_dos_testes, BigDecimal v) {
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

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Deprecated
    public Proposta(){}

    public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome, @NotBlank String endereco, @Positive BigDecimal salario, StatusProposta statusProposta) {
        this.documento = documento.replaceAll("[^0-9]", "");
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.statusProposta = statusProposta;
    }

    public AnalisePropostaRequest toAnalise(){
        return new AnalisePropostaRequest(this.documento, this.nome, this.id);
    }

    public SolicitaCartaoRequest toSolicitaCartao(){
        return new SolicitaCartaoRequest(this.id);
    }

    public ConsultaPropostaResponse toConsultaProposta() {
        return new ConsultaPropostaResponse(this.id, this.documento, this.statusProposta);

    }
}
