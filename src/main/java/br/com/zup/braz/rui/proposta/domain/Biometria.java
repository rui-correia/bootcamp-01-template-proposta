package br.com.zup.braz.rui.proposta.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_biometria")
public class Biometria {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(columnDefinition = "TEXT")
    private String biometria;
    @NotBlank
    private String idCartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String biometria, @NotBlank String idCartao) {
        this.biometria = biometria;
        this.idCartao = idCartao;
    }

    public Biometria(String biometria) {
        this.biometria = biometria;
    }

    public String getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }

    public String getIdCartao() {
        return idCartao;
    }
}
