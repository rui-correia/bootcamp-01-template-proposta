package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Bloqueio;
import br.com.zup.braz.rui.proposta.domain.Cartao;
import br.com.zup.braz.rui.proposta.domain.StatusBloqueio;
import br.com.zup.braz.rui.proposta.repository.BloqueioRepository;
import br.com.zup.braz.rui.proposta.response.BloqueioResponse;
import br.com.zup.braz.rui.proposta.service.BloqueioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bloquear_cartao")
public class BloqueiaCartaoController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BloqueioService bloqueioService;//1

    @Autowired
    HttpServletRequest request;

    public final Logger logger = LoggerFactory.getLogger(BloqueiaCartaoController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<?> bloquearCartao(@RequestParam("idCartao") String idCartao, UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, idCartao);//1
        if (cartao == null) {//1
            logger.info("Cartão não foi encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado.");
        }
        String ipClienteSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        if (bloqueioService.isBloqueioAtivo(idCartao)){//1
            logger.info("Cartão já se encontra bloqueado.");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já se encontra bloqueado.");
        }

        Bloqueio bloqueioCartao = new Bloqueio(idCartao, ipClienteSolicitante, userAgent, StatusBloqueio.SOLICITADO);//1
        logger.info("Salvando novo bloqueio.");
        bloqueioService.criar(bloqueioCartao);

        BloqueioResponse bloqueioResponse = new BloqueioResponse(bloqueioCartao.getId());
        return ResponseEntity.created(uriComponentsBuilder.path("/consultaBloqueio/{idBloqueio}").buildAndExpand(bloqueioResponse.getId()).toUri()).body(bloqueioResponse);
    }
}
