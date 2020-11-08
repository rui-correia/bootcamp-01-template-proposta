package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.request.NovaPropostaRequest;
import br.com.zup.braz.rui.proposta.response.PropostaResponse;
import br.com.zup.braz.rui.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/novaProposta")
public class NovaPropostaController {

    @Autowired
    PropostaService propostaService;

    @PostMapping
    @Transactional                                              //1
    public ResponseEntity<?> novaProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
        Proposta proposta = novaPropostaRequest.toModel(); //1
        propostaService.criar(proposta);
        PropostaResponse propostaResponse = new PropostaResponse(proposta.getId(), proposta.getDocumento());
        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(propostaResponse.getId()).toUri()).body(propostaResponse);
    }
}
