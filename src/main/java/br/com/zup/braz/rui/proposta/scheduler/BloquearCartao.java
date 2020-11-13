package br.com.zup.braz.rui.proposta.scheduler;

import br.com.zup.braz.rui.proposta.domain.Bloqueio;
import br.com.zup.braz.rui.proposta.service.BloqueioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@EnableAsync
public class BloquearCartao {

    @Autowired
    BloqueioService bloqueioService;

    @Scheduled(fixedRate = 15000)
    void bloquearCartao() {
        List<Bloqueio> bloqueios = bloqueioService.buscarSolicitacoesDeBloqueio();
        if (!bloqueios.isEmpty()) {
            for (Bloqueio bloqueio : bloqueios) {
                bloqueioService.bloquearCartao(bloqueio);
            }
        }

    }

}
