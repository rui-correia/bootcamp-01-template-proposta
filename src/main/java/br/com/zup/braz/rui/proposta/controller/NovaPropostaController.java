package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.domain.StatusAnalise;
import br.com.zup.braz.rui.proposta.feign.AnaliseClient;
import br.com.zup.braz.rui.proposta.request.NovaPropostaRequest;
import br.com.zup.braz.rui.proposta.response.AnalisePropostaResponse;
import br.com.zup.braz.rui.proposta.service.PropostaService;
import feign.FeignException;
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
    PropostaService propostaService;

    @Autowired
    AnaliseClient analiseClient; //1

    @PostMapping
    @Transactional                                              //1
    public ResponseEntity<?> novaProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
        Proposta proposta = novaPropostaRequest.toModel(); //1
        propostaService.criar(proposta);
        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).body(proposta);
    }
}
