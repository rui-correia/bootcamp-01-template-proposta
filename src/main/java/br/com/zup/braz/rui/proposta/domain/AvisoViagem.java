package br.com.zup.braz.rui.proposta.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_avisoviagem")
public class AvisoViagem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank
    private String idCartao;
    @NotBlank
    private String destino;
    @NotNull
    private LocalDate termino;
    @NotNull
    private LocalDateTime instanteAviso = LocalDateTime.now();
    @NotBlank
    private String ipSolicitante;
    @NotBlank
    private String userAgent;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(@NotBlank String idCartao, @NotBlank String destino, @NotNull LocalDate termino, @NotBlank String ipSolicitante, @NotBlank String userAgent) {
        this.idCartao = idCartao;
        this.destino = destino;
        this.termino = termino;
        this.ipSolicitante = ipSolicitante;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }
}
