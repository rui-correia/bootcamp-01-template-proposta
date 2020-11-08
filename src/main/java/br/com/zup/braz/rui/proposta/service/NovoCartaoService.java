package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Cartao;
import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.domain.StatusProposta;
import br.com.zup.braz.rui.proposta.feign.CartoesClient;
import br.com.zup.braz.rui.proposta.request.SolicitaCartaoRequest;
import br.com.zup.braz.rui.proposta.response.SolicitaCartaoResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class NovoCartaoService {

    private final Logger logger = LoggerFactory.getLogger(NovoCartaoService.class);

    @Autowired
    CartoesClient cartoesClient;//1

    @Autowired
    EntityManager entityManager;

    @Transactional
    public Cartao gravar(SolicitaCartaoRequest solicitaCartaoRequest) {//1

        SolicitaCartaoResponse solicitaCartaoResponse = null;//1

        try {//1

            logger.info("Iniciando solicitação de cartão.");
            solicitaCartaoResponse = cartoesClient.solicitaCartao(solicitaCartaoRequest.idProposta).getBody();
            logger.info("Alterando status da proposta para FINALIZADO.");

            //TODO gravar cartao no banco
            Cartao cartao = solicitaCartaoResponse.toModel();
            entityManager.persist(cartao);
            logger.info("Gravação do cartão finalizada.");
            //TODO alterar o status da proposta
            alterarStatusPropostaCartaoGerado(solicitaCartaoRequest);
        } catch (FeignException e) {//1
            System.out.println(e.getMessage());

        }

        return null;
    }

    @Transactional
    public void alterarStatusPropostaCartaoGerado(SolicitaCartaoRequest solicitaCartaoRequest) {
        Proposta proposta = entityManager.find(Proposta.class, solicitaCartaoRequest.idProposta);
        proposta.setStatusProposta(StatusProposta.FINALIZADO);
        entityManager.merge(proposta);

    }
}
