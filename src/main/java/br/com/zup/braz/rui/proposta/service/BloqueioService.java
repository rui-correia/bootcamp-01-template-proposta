package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Bloqueio;
import br.com.zup.braz.rui.proposta.repository.BloqueioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.zup.braz.rui.proposta.domain.StatusBloqueio.BLOQUEADO;
import static br.com.zup.braz.rui.proposta.domain.StatusBloqueio.SOLICITADO;

@Service
public class BloqueioService {

    @Autowired
    BloqueioRepository bloqueioRepository;//1


    public boolean isBloqueioAtivo(String idCartao) {
        List<Enum> status = new ArrayList<>();
        status.add(BLOQUEADO);
        status.add(SOLICITADO);
        Optional<Bloqueio> bloqueio = bloqueioRepository.findByIdCartaoAndStatusIn(idCartao, status);
        if (bloqueio.isPresent()) {//1
            return true;
        }
        return false;
    }

    public void criar(Bloqueio bloqueioCartao) {//1
        bloqueioRepository.save(bloqueioCartao);
    }

    public void bloquearCartao(String idCartao){

    }
}
