package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.configuration.error.ApiErroException;
import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.request.NovaPropostaRequest;
import br.com.zup.braz.rui.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/novaProposta")
public class NovaPropostaController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PropostaService propostaService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder){
        Proposta proposta = novaPropostaRequest.toModel();

        if (propostaService.existeDocumento(proposta.getDocumento())) {
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta para este CPF.");
            //return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(novaPropostaRequest);
        }
        entityManager.persist(proposta);

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).body(proposta);
    }
}
