package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Bloqueio;
import br.com.zup.braz.rui.proposta.feign.CartoesClient;
import br.com.zup.braz.rui.proposta.repository.BloqueioRepository;
import br.com.zup.braz.rui.proposta.request.BloqueioRequest;
import br.com.zup.braz.rui.proposta.response.BloquearCartaoResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.zup.braz.rui.proposta.domain.StatusBloqueio.BLOQUEADO;
import static br.com.zup.braz.rui.proposta.domain.StatusBloqueio.SOLICITADO;

@Service
public class BloqueioService {

    @Autowired
    BloqueioRepository bloqueioRepository;//1

    @Autowired
    CartoesClient cartoesClient;//1

    private final Logger logger = LoggerFactory.getLogger(BloqueioService.class);

    //@Value("${spring.application.name}")
    private String sistemaResponsavel = "proposta";

    public boolean isBloqueioAtivo(String idCartao) {
        List<Enum> status = new ArrayList<>();
        status.add(BLOQUEADO);
        status.add(SOLICITADO);
        Optional<Bloqueio> bloqueio = bloqueioRepository.findByIdCartaoAndStatusIn(idCartao, status);
        if (bloqueio.isPresent()) {//1
            return true;
        }
        return false;
    }

    public void criar(Bloqueio bloqueioCartao) {//1
        bloqueioRepository.save(bloqueioCartao);
    }

    public void bloquearCartao(Bloqueio bloqueio) {

            logger.info("Iniciando solicitação de bloqueio de cartão.");
            ResponseEntity responseEntity = cartoesClient.bloquearCartao(bloqueio.getIdCartao(), new BloqueioRequest(sistemaResponsavel));

            if (responseEntity.getStatusCode().is2xxSuccessful()) {//1
                bloqueio.setStatus(BLOQUEADO);
                bloqueioRepository.save(bloqueio);
                logger.info("Cartão bloqueado com sucesso.");
            }else {
                return;
            }

    }

    public List<Bloqueio> buscarSolicitacoesDeBloqueio() {
        List<Bloqueio> cartoesASeremBloqueados = bloqueioRepository.findAllByStatus(SOLICITADO);

        return cartoesASeremBloqueados;
    }
}
