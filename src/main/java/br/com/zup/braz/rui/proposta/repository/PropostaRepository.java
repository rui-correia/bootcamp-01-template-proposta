package br.com.zup.braz.rui.proposta.repository;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Proposta findByDocumento(String documento);
}
