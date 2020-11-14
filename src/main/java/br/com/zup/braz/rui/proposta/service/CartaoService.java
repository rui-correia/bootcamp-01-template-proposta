package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public boolean verificaCartaoExistente(String idCartao) {
        return cartaoRepository.existsById(idCartao);
    }
}
