package br.com.zup.braz.rui.proposta.domain;

import br.com.zup.braz.rui.proposta.request.BloqueioRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_bloqueiocartao")
public class Bloqueio {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank
    String idCartao;
    @NotNull
    LocalDateTime instanteBloqueio = LocalDateTime.now();
    @NotBlank
    String ipClienteSolicitante;
    @NotBlank
    String userAgent;
    @NotNull
    @Enumerated(EnumType.STRING)
    StatusBloqueio status;


    @Deprecated
    Bloqueio() {

    }

    public Bloqueio(@NotBlank String idCartao, @NotBlank String ipClienteSolicitante, @NotBlank String userAgent, StatusBloqueio status) {
        this.idCartao = idCartao;
        this.ipClienteSolicitante = ipClienteSolicitante;
        this.userAgent = userAgent;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public void setStatus(StatusBloqueio status) {
        this.status = status;
    }
}
