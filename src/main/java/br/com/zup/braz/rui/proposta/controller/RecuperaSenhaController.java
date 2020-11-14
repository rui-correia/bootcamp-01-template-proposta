package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Cartao;
import br.com.zup.braz.rui.proposta.domain.RecuperaSenha;
import br.com.zup.braz.rui.proposta.repository.CartaoRepository;
import br.com.zup.braz.rui.proposta.response.RecuperaSenhaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/recupera_senha")
public class RecuperaSenhaController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    HttpServletRequest request;

    @Autowired
    CartaoRepository cartaoRepository;//1

    public final Logger logger = LoggerFactory.getLogger(RecuperaSenhaController.class);

    @PostMapping(value = "/{idCartao}")
    @Transactional
    public ResponseEntity<?> recuperarSenha(@PathVariable String idCartao, UriComponentsBuilder uriComponentsBuilder) {

        if (!cartaoRepository.findById(idCartao).isPresent()) {//1
            logger.info("Cartão não foi encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado.");
        }

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");
        RecuperaSenha recuperaSenha = new RecuperaSenha(idCartao, ipSolicitante, userAgent);//1
        entityManager.persist(recuperaSenha);
        RecuperaSenhaResponse recuperaSenhaResponse = new RecuperaSenhaResponse(recuperaSenha.getId());//1
        return ResponseEntity.created(uriComponentsBuilder.path("consultaRecuperarSenha/{id}").buildAndExpand(recuperaSenha.getId()).toUri()).body(recuperaSenhaResponse);
    }
}
