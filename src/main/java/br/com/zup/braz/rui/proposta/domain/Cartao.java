package br.com.zup.braz.rui.proposta.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_cartao")
public class Cartao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    //@OneToMany(mappedBy = "t_cartao")
    //private List<Bloqueio> bloqueios;
    //@OneToMany(mappedBy = "t_cartao")
    //private List<Aviso> avisos;
    //@OneToMany(mappedBy = "t_cartao")
    //private List<Carteira> carteiras;
    //@OneToMany(mappedBy = "t_cartao")
    //private List<Parcela> parcelas;
    @NotNull
    private BigDecimal limite;

    private boolean renegociacao;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vencimento")
    private Vencimento vencimento;
    @NotBlank
    private String idProposta;

    @Deprecated
    public Cartao() {

    }

    public Cartao(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular, List<Bloqueio> bloqueios, List<AvisoViagem> avisos, List<Carteira> carteiras, List<Parcela> parcelas, @NotNull BigDecimal limite, @NotBlank boolean renegociacao, @NotBlank Vencimento vencimento, @NotBlank String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
//        this.bloqueios = bloqueios;
//        this.avisos = avisos;
//        this.carteiras = carteiras;
//        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }
}
