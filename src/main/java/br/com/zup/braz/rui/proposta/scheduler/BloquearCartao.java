package br.com.zup.braz.rui.proposta.scheduler;

import br.com.zup.braz.rui.proposta.domain.Bloqueio;
import br.com.zup.braz.rui.proposta.domain.StatusBloqueio;
import br.com.zup.braz.rui.proposta.repository.BloqueioRepository;
import ch.qos.logback.core.db.dialect.HSQLDBDialect;
import org.hibernate.engine.jdbc.spi.SqlStatementLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

import static br.com.zup.braz.rui.proposta.domain.StatusBloqueio.SOLICITADO;

@Configuration
@EnableScheduling
@EnableAsync
public class BloquearCartao {

    @Autowired
    BloqueioRepository bloqueioRepository;

    void bloquearCartao() {

        List<Bloqueio> bloqueios = buscarSolicitacoesDeBloqueio();
        if (!bloqueios.isEmpty()) {
            for (Bloqueio bloqueio : bloqueios) {

            }
        }

    }


    private List<Bloqueio> buscarSolicitacoesDeBloqueio() {
        List<Bloqueio> cartoesASeremBloqueados = (List<Bloqueio>) bloqueioRepository.findAllByStatus(SOLICITADO);

        return cartoesASeremBloqueados;
    }

}
