package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    @Autowired
    PropostaRepository propostaRepository;

    //Verifica se ja existe uma proposta para esse documento no banco.
    public boolean existeDocumento(String documento){
        if (propostaRepository.findByDocumento(documento) != null) {
            return true;
        }
        return false;
    }
}
