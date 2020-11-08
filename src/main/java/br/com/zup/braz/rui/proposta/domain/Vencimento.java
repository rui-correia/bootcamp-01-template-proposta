package br.com.zup.braz.rui.proposta.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_vencimento")
public class Vencimento {

    @Id
    @NotBlank
    private String id;
    @NotNull
    @Positive
    @Max(31)
    @Min(01)
    private Integer dia;
    @NotNull
    private LocalDateTime dataDeCriacao;
    @OneToMany
    @JoinColumn(name = "id_vencimento")
    private List<Cartao> cartao;


    public Vencimento(@NotBlank String id, @NotNull @Positive @Max(31) @Min(01) Integer dia, @NotNull LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}
