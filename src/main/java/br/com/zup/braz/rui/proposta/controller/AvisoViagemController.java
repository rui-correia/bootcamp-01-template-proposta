package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.AvisoViagem;
import br.com.zup.braz.rui.proposta.request.AvisoViagemRequest;
import br.com.zup.braz.rui.proposta.response.AvisoViagemResponse;
import br.com.zup.braz.rui.proposta.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/aviso_viagem")
public class AvisoViagemController {

    @Autowired
    CartaoService cartaoService;//1

    @Autowired
    HttpServletRequest request;

    @Autowired
    EntityManager entityManager;

    @PostMapping(value = "/{idCartao}")
    @Transactional
    public ResponseEntity<?> avisarViagem(@PathVariable String idCartao, @RequestBody @Valid AvisoViagemRequest avisoViagemRequest, UriComponentsBuilder uriComponentsBuilder) {

        if (!cartaoService.verificaCartaoExistente(idCartao)) {//1
            return ResponseEntity.notFound().build();
        }

        AvisoViagem avisoViagem = new AvisoViagem(idCartao, avisoViagemRequest.getDestino(), avisoViagemRequest.getTermino(), request.getRemoteHost(), request.getHeader("User-Agent"));//1
        entityManager.persist(avisoViagem);

        AvisoViagemResponse avisoViagemResponse = new AvisoViagemResponse(avisoViagem.getId());//1
        return ResponseEntity.created(uriComponentsBuilder.path("/consulta_aviso/{id}").buildAndExpand(avisoViagemResponse.getId()).toUri()).body(avisoViagemResponse);
    }
}
