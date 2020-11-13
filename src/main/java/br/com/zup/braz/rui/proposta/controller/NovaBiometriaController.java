package br.com.zup.braz.rui.proposta.controller;

import br.com.zup.braz.rui.proposta.domain.Biometria;
import br.com.zup.braz.rui.proposta.response.NovaBiometriaResponse;
import br.com.zup.braz.rui.proposta.service.NovaBiometriaService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;

import java.io.IOException;

@RestController
@RequestMapping("/novaBiometria")
public class NovaBiometriaController {

    @Autowired
    NovaBiometriaService novaBiometriaService;//1

    public final Logger logger = LoggerFactory.getLogger(NovaBiometriaController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaBiometria(@RequestParam("biometria") MultipartFile biometria, UriComponentsBuilder uriComponentsBuilder, @RequestParam("idCartao") String idCartao) {

        if (biometria.isEmpty()){
            logger.info("Biometria nula ou inválida.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!novaBiometriaService.verificaCartaoExistente(idCartao)) {//1
            logger.info("Cartão não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("Convertendo imagem para String com Base64.");
        String biometriaEmString = novaBiometriaService.converterImagemParaString(biometria);

        Biometria objetoBiometria = novaBiometriaService.salvarBiometria(biometriaEmString, idCartao);//1

        NovaBiometriaResponse novaBiometriaResponse = new NovaBiometriaResponse(objetoBiometria.getId(), objetoBiometria.getBiometria());//1
        return ResponseEntity.created(uriComponentsBuilder.path("/consultaBiometria/{idBiometria}").buildAndExpand(novaBiometriaResponse.getId()).toUri()).body(novaBiometriaResponse);
    }

}
