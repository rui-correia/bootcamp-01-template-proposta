package br.com.zup.braz.rui.proposta.repository;

import br.com.zup.braz.rui.proposta.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {

    Cartao findByIdProposta(String idProposta);
}
