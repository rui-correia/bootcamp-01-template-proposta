package br.com.zup.braz.rui.proposta.feign;

import br.com.zup.braz.rui.proposta.response.SolicitaCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@FeignClient(name = "cartoes", url = "http://localhost:8888")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET, path = "/api/cartoes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<SolicitaCartaoResponse> solicitaCartao(@RequestParam("idProposta") String idProposta);
}