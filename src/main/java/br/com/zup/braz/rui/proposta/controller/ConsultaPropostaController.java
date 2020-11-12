package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.response.ConsultaPropostaResponse;
import br.com.zup.braz.rui.proposta.service.AssociaCartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
public class ConsultaPropostaController {

    @Autowired
    EntityManager entityManager;

    public final Logger logger = LoggerFactory.getLogger(ConsultaPropostaController.class);

    @RequestMapping(path = "/consultaProposta", method = RequestMethod.GET)
    public ResponseEntity<ConsultaPropostaResponse> consultaProposta(@RequestParam("idProposta") String idProposta) {//1

        Proposta proposta = entityManager.find(Proposta.class, idProposta);//1
        if (proposta == null) {
            logger.info("Proposta: " + idProposta + " não encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ConsultaPropostaResponse propostaResponse = proposta.toConsultaProposta();

        return ResponseEntity.status(HttpStatus.OK).body(propostaResponse);
    }
}
