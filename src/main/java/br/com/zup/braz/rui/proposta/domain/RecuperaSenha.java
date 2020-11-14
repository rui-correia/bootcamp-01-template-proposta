package br.com.zup.braz.rui.proposta.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_recuperasenha")
public class RecuperaSenha {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank
    private String idCartao;
    @NotNull
    private LocalDateTime horaSolicitacao = LocalDateTime.now();
    @NotBlank
    private String ipSolicitante;
    @NotBlank
    private String userAgent;

    @Deprecated
    public RecuperaSenha(){}

    public RecuperaSenha(@NotBlank String idCartao, @NotBlank String ipSolicitante, @NotBlank String userAgent) {
        this.idCartao = idCartao;
        this.ipSolicitante = ipSolicitante;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }
}
