package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Cartao;
import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.domain.StatusProposta;
import br.com.zup.braz.rui.proposta.repository.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class AssociaCartaoService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CartaoRepository cartaoRepository;

    public final Logger logger = LoggerFactory.getLogger(AssociaCartaoService.class);

    @Transactional
    public void alterarStatusProposta(String idProposta, StatusProposta status) {
        logger.info("Iniciando alteração do status da proposta para: " + status.toString());
        Proposta proposta = entityManager.find(Proposta.class, idProposta);//1
        proposta.setStatusProposta(status);
        entityManager.merge(proposta);
        logger.info("Status da proposta alterado para: " + status.toString());

    }


    @Transactional
    public void associarCartaoAProposta(Proposta proposta) {
        logger.info("Buscando cartão no banco.");
        Cartao cartao = cartaoRepository.findByIdProposta(proposta.getId());
        logger.info("Atrelando o cartão à proposta");
        proposta.setNumeroCartao(cartao.getId());
        entityManager.merge(proposta);
        logger.info("Proposta atualizada com cartão.");
    }
}
