package br.com.zup.braz.rui.proposta.service;

import br.com.zup.braz.rui.proposta.domain.Biometria;
import br.com.zup.braz.rui.proposta.repository.CartaoRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;

@Service
public class NovaBiometriaService {

    private final Logger logger = LoggerFactory.getLogger(NovaBiometriaService.class);

    @Autowired
    CartaoRepository cartaoRepository;//1

    @Autowired
    EntityManager entityManager;


    public boolean verificaCartaoExistente(String idCartao) {
        return cartaoRepository.existsById(idCartao);
    }

    public Biometria salvarBiometria(String biometria, String idCartao) {

        Biometria novaBiometria = new Biometria(biometria, idCartao);//1
        entityManager.persist(novaBiometria);
        return novaBiometria;
    }

    public String converterImagemParaString(MultipartFile imagem) {
        String imagemEmString = null;
        try {//1
            imagemEmString = Base64.encodeBase64String(imagem.getBytes());
        } catch (IOException exception) {//1
            logger.error("Ocorreu uma falha ao salvar a biometria." + exception.getCause());
            exception.printStackTrace();
        }
        return imagemEmString;
    }
}
