package br.com.zup.braz.rui.proposta.configuration.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class Criptografia {

    private static String tokenCriptografia;

    public Criptografia(@Value("${token.criptografia}") String tokenCriptografia) {
        this.tokenCriptografia = tokenCriptografia;
    }

    public static String encriptar(String documento) {
        Assert.notNull(documento, "Documento para criptografar não pode ser nulo.");
        StringBuilder builder = new StringBuilder();
        String hash = DigestUtils.sha256Hex(builder.append(documento).append(tokenCriptografia).toString());
        return hash;
    }
}
