package br.com.zup.braz.rui.proposta.repository;

import br.com.zup.braz.rui.proposta.domain.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, String> {

    List<Proposta> findAllByStatusProposta(Enum status);

    List<Proposta> findAllByStatusPropostaIn(List<Enum> status);
}
