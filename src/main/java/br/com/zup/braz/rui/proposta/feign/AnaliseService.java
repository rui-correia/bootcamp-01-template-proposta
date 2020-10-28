package br.com.zup.braz.rui.proposta.feign;

import br.com.zup.braz.rui.proposta.request.AnalisePropostaRequest;
import br.com.zup.braz.rui.proposta.response.AnalisePropostaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseService", url = "http://localhost:9999")
public interface AnaliseService {

    @PostMapping("/api/solicitacao")
    public AnalisePropostaResponse analisaProposta(@RequestBody AnalisePropostaRequest analisePropostaRequest);

}
