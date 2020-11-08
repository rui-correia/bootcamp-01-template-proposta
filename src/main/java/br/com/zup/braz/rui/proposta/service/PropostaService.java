package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.domain.StatusProposta;
import br.com.zup.braz.rui.proposta.exception.DadosImprocessaveisException;
import br.com.zup.braz.rui.proposta.feign.AnaliseClient;
import br.com.zup.braz.rui.proposta.repository.PropostaRepository;
import br.com.zup.braz.rui.proposta.response.AnalisePropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository; //1

    @Autowired
    AnaliseClient analiseClient; //2
            //3
    public Proposta criar(Proposta propostaCriada) {
        propostaRepository.save(propostaCriada);
        analisaProposta(propostaCriada);
        return propostaCriada;
    }

    public void analisaProposta(Proposta proposta) {
        //4
        AnalisePropostaResponse analisePropostaResponse = null;
        try { //5
            analisePropostaResponse = analiseClient.analisaProposta(proposta.toAnalise()).getBody();
            proposta.setStatusProposta(analisePropostaResponse.getStatusAnalise().toPropostaStatus());
        } catch (DadosImprocessaveisException exception) { //6
            if(exception.getHttpStatus() == HttpStatus.UNPROCESSABLE_ENTITY) { //7
                proposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
            }
        }
        propostaRepository.save(proposta);
    }
}
