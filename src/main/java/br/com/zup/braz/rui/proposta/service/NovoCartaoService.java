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
import javax.persistence.PersistenceContext;

@Service
public class NovoCartaoService {

    private final Logger logger = LoggerFactory.getLogger(NovoCartaoService.class);

    @Autowired
    CartoesClient cartoesClient;//1

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Cartao gravar(SolicitaCartaoRequest solicitaCartaoRequest) {//1

        SolicitaCartaoResponse solicitaCartaoResponse = null;//1

        try {//1

            logger.info("Iniciando solicitação de cartão.");
            solicitaCartaoResponse = cartoesClient.solicitaCartao(solicitaCartaoRequest.idProposta).getBody();

            //gravar cartao no banco
            Cartao cartao = solicitaCartaoResponse.toModel();//1
            entityManager.merge(cartao);
            logger.info("Gravação do cartão finalizada.");
            logger.info("Alterando status da proposta para APROVADO.");
            alterarStatusPropostaCartaoGerado(solicitaCartaoRequest);
        } catch (FeignException e) {//1
            logger.error("Ocorreu um erro ao analisar proposta: " + solicitaCartaoRequest.idProposta);
            logger.error(e.getMessage());

        }

        return null;
    }

    @Transactional
    public void alterarStatusPropostaCartaoGerado(SolicitaCartaoRequest solicitaCartaoRequest) {
        Proposta proposta = entityManager.find(Proposta.class, solicitaCartaoRequest.idProposta);//1
        proposta.setStatusProposta(StatusProposta.APROVADO);
        entityManager.merge(proposta);

    }
}
