package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Carteira;
import br.com.zup.braz.rui.proposta.domain.NomeCarteira;
import br.com.zup.braz.rui.proposta.feign.CartoesClient;
import br.com.zup.braz.rui.proposta.request.CarteiraRequest;
import br.com.zup.braz.rui.proposta.response.CarteiraResponse;
import br.com.zup.braz.rui.proposta.service.CartaoService;
import br.com.zup.braz.rui.proposta.service.CarteiraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/nova_carteira")
public class NovaCarteiraController {

    public final Logger logger = LoggerFactory.getLogger(NovaCarteiraController.class);

    @Autowired
    CarteiraService carteiraService;//1

    @Autowired
    CartoesClient cartoesClient;//1

    @PostMapping(value = "/{idCartao}")
    public ResponseEntity<?> criarNovaCarteira(@PathVariable String idCartao, @RequestBody @Valid CarteiraRequest carteiraRequest, UriComponentsBuilder uriComponentsBuilder) {

        if (!carteiraService.verificaCartaoExistente(idCartao)) {//1
            return ResponseEntity.notFound().build();
        }
        if(carteiraService.carteiraExists(idCartao,carteiraRequest.getCarteira())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Esta carteira já está cadastrada.");
        }

        ResponseEntity responseEntity = cartoesClient.novaCarteira(idCartao, carteiraRequest);
        if (responseEntity.getStatusCode().is2xxSuccessful()){//1
            Carteira carteira = new Carteira(idCartao, carteiraRequest.getEmail(), carteiraRequest.getCarteira());//1
            carteiraService.salvar(carteira);
            CarteiraResponse carteiraResponse = new CarteiraResponse(carteira.getId());
            return ResponseEntity.created(uriComponentsBuilder.path("consultaCarteira/{id}").buildAndExpand(carteiraResponse.getId()).toUri()).body(carteiraResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
