package br.com.zup.braz.rui.proposta.request;

import java.time.LocalDate;

public class AvisoViagemRequest {
    private String destino;
    private LocalDate termino;

    public AvisoViagemRequest(String destino, LocalDate termino) {
        this.destino = destino;
        this.termino = termino;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        this.termino = termino;
    }
}
