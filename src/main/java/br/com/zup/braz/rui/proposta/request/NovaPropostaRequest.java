package br.com.zup.braz.rui.proposta.request;

import br.com.zup.braz.rui.proposta.annotation.CpfCnpj;
import br.com.zup.braz.rui.proposta.annotation.DocumentoUnico;
import br.com.zup.braz.rui.proposta.domain.Proposta;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {


    @DocumentoUnico(fieldName = "documento", domainClass = Proposta.class)
    @CpfCnpj(fieldName = "documento", domainClass = Proposta.class)
    @NotBlank(message = "Documento deve ser inserido.")
    private String documento;
    @NotBlank(message = "Email deve ser inserido.")
    @Email(message = "Formato de email invalido.")
    private String email;
    @NotBlank(message = "Nome deve ser inserido.")
    private String nome;
    @NotBlank(message = "Endereço deve ser inserido.")
    private String endereco;
    @NotNull
    @Positive(message = "Salário inserido deve ser maior que zero.")
    private BigDecimal salario;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }

}
