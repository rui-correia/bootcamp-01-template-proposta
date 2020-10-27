package br.com.zup.braz.rui.proposta.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;



@Entity
@Table(name = "t_proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    @Deprecated
    public Proposta(){}

    public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome, @NotBlank String endereco, @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
}
