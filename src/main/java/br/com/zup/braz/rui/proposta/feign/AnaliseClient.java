package br.com.zup.braz.rui.proposta.feign;

import br.com.zup.braz.rui.proposta.request.AnalisePropostaRequest;
import br.com.zup.braz.rui.proposta.response.AnalisePropostaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "analise", url = "http://localhost:9999")
public interface AnaliseClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    ResponseEntity<AnalisePropostaResponse> analisaProposta(AnalisePropostaRequest analisePropostaRequest);

}
