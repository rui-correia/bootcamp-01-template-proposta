package br.com.zup.braz.rui.proposta.response;

import br.com.zup.braz.rui.proposta.domain.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class SolicitaCartaoResponse {

    @NotBlank
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    private List<Bloqueio> bloqueios;
    private List<AvisoViagem> avisos;
    private List<Carteira> carteiras;
    private List<Parcela> parcelas;
    @NotNull
    private BigDecimal limite;
    private boolean renegociacao;
    @NotBlank
    private Vencimento vencimento;

    @NotBlank
    private String idProposta;

    public SolicitaCartaoResponse(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular, List<Bloqueio> bloqueios, List<AvisoViagem> avisos, List<Carteira> carteiras, List<Parcela> parcelas, @NotNull BigDecimal limite, boolean renegociacao, @NotBlank Vencimento vencimento, @NotBlank String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public void setEmitidoEm(LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public void setBloqueios(List<Bloqueio> bloqueios) {
        this.bloqueios = bloqueios;
    }

    public List<AvisoViagem> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<AvisoViagem> avisos) {
        this.avisos = avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public void setCarteiras(List<Carteira> carteiras) {
        this.carteiras = carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public boolean isRenegociacao() {
        return renegociacao;
    }

    public void setRenegociacao(boolean renegociacao) {
        this.renegociacao = renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public void setVencimento(Vencimento vencimento) {
        this.vencimento = vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }

    public Cartao toModel() {
        return new Cartao(this.id, this.emitidoEm,this.titular, this.bloqueios, this.avisos, this.carteiras,this.parcelas, this.limite, this.renegociacao,this.vencimento,this.idProposta);
    }
}
