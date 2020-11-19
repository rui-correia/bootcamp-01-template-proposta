package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.request.NovaPropostaRequest;
import br.com.zup.braz.rui.proposta.response.PropostaResponse;
import br.com.zup.braz.rui.proposta.service.PropostaService;
import io.opentracing.Tracer;
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
@RequestMapping(value = "/nova_proposta")
public class NovaPropostaController {

    @Autowired
    PropostaService propostaService;//1

    @Autowired
    private Tracer tracer;//Testando o Tracer

    @PostMapping
    @Transactional                                              //1
    public ResponseEntity<?> novaProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
        tracer.activeSpan().setTag("proposta.email", novaPropostaRequest.getEmail());
        tracer.activeSpan().setBaggageItem("proposta.email", novaPropostaRequest.getEmail());
        tracer.activeSpan().log("Cadastrando nova proposta");

        if (propostaService.documentoExistente(novaPropostaRequest)){
            return ResponseEntity.unprocessableEntity().body("Ja existe uma proposta cadastrada com o documento informado.");
        }

        Proposta proposta = novaPropostaRequest.toModel(); //1
        proposta.setDocumento(Proposta.criptografarDocumento(novaPropostaRequest.getDocumento()));
        propostaService.criar(proposta);
        PropostaResponse propostaResponse = new PropostaResponse(proposta.getId(), novaPropostaRequest.getDocumento());
        return ResponseEntity.created(uriComponentsBuilder.path("/consultaProposta/{id}").buildAndExpand(propostaResponse.getId()).toUri()).body(propostaResponse);
    }
}
