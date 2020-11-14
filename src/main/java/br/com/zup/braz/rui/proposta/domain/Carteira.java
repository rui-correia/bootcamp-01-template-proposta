package br.com.zup.braz.rui.proposta.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_carteira")
public class Carteira {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank
    private String idCartao;
    @NotBlank
    private String email;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private NomeCarteira nomeCarteira;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String idCartao, String email, NomeCarteira nomeCarteira) {
        this.idCartao = idCartao;
        this.email = email;
        this.nomeCarteira = nomeCarteira;
    }

    public String getId() {
        return id;
    }
}
