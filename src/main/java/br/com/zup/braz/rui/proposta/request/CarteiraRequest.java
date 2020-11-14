package br.com.zup.braz.rui.proposta.request;

import br.com.zup.braz.rui.proposta.domain.NomeCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {
    @NotBlank
    @Email
    private String email;
    @NotNull
    private NomeCarteira carteira;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NomeCarteira getCarteira() {
        return carteira;
    }

    public void setCarteira(NomeCarteira carteira) {
        this.carteira = carteira;
    }
}
