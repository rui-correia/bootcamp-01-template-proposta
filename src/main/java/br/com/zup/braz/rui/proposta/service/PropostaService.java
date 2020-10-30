package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.domain.StatusAnalise;
import br.com.zup.braz.rui.proposta.domain.StatusProposta;
import br.com.zup.braz.rui.proposta.exception.DadosImprocessaveisException;
import br.com.zup.braz.rui.proposta.feign.AnaliseClient;
import br.com.zup.braz.rui.proposta.repository.PropostaRepository;
import br.com.zup.braz.rui.proposta.response.AnalisePropostaResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.zup.braz.rui.proposta.domain.StatusProposta.*;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    AnaliseClient analiseClient;

    public Proposta criar(Proposta propostaCriada) {
        propostaRepository.save(propostaCriada);
        analisaProposta(propostaCriada);
        return propostaCriada;
    }

    public void analisaProposta(Proposta proposta) {

        AnalisePropostaResponse analisePropostaResponse = null;
        try {
            analisePropostaResponse = analiseClient.analisaProposta(proposta.toAnalise()).getBody();
            proposta.setStatusProposta(analisePropostaResponse.getStatusAnalise().toPropostaStatus());
        } catch (DadosImprocessaveisException exception) {
            //proposta.setStatusProposta(new StatusProposta(NAO_ELEGIVEL));
        }
        propostaRepository.save(proposta);
    }

}
