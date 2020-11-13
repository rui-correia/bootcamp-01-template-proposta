package br.com.zup.braz.rui.proposta.scheduler;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.repository.PropostaRepository;
import br.com.zup.braz.rui.proposta.service.AssociaCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

import static br.com.zup.braz.rui.proposta.domain.StatusProposta.*;

@Configuration
@EnableAsync
@EnableScheduling
public class AssociaCartaoProposta {

    @Autowired
    PropostaRepository propostaRepository;//1

    @Autowired
    AssociaCartaoService associaCartaoService;//1

    @Scheduled(fixedRate = 15000)
    void associaCartaoProposta() {
        List<Proposta> propostaList = buscaPropostasAprovadas();//1
        if (!propostaList.isEmpty()) {//1
            for (Proposta proposta : propostaList) {//1
                associaCartaoService.alterarStatusProposta(proposta.getId(), EM_PROCESSAMENTO);
                associaCartaoService.associarCartaoAProposta(proposta);
                associaCartaoService.alterarStatusProposta(proposta.getId(), FINALIZADO);
            }
        }
    }


    private List<Proposta> buscaPropostasAprovadas() {
        List<Enum> status = new ArrayList<>();
        status.add(APROVADO);
        status.add(EM_PROCESSAMENTO);
        List<Proposta> propostasAprovadas = propostaRepository.findAllByStatusPropostaIn(status);
        return propostasAprovadas;
    }
}
