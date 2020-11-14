package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Carteira;
import br.com.zup.braz.rui.proposta.domain.NomeCarteira;
import br.com.zup.braz.rui.proposta.repository.CartaoRepository;
import br.com.zup.braz.rui.proposta.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarteiraService {

    @Autowired
    CarteiraRepository carteiraRepository;//1

    @Autowired
    CartaoRepository cartaoRepository;//1

    public boolean verificaCartaoExistente(String idCartao) {
        return cartaoRepository.existsById(idCartao);
    }

    public boolean carteiraExists(String idCartao, NomeCarteira nomeCarteira) {
        Optional<Carteira> carteira = carteiraRepository.findByIdCartaoAndNomeCarteira(idCartao, nomeCarteira);

        if (carteira.isPresent()) {//1
            return true;
        }
        return false;
    }


    public void salvar(Carteira carteira) {
        carteiraRepository.save(carteira);
    }
}
