package br.com.zup.braz.rui.proposta;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import br.com.zup.braz.rui.proposta.service.PropostaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PropostaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PropostaService propostaService;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Teste para criação de nova proposta elegivel")
	void criarNovaProposta() throws Exception {
		Proposta proposta = new Proposta("13206222400", "teste@gmail.com", "testeUm", "Avenida dos testes", new BigDecimal(850.00));

		mockMvc.perform(post("novaProposta")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(proposta)))
				.andExpect(status().isOk());

	}


}
