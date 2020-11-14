package br.com.zup.braz.rui.proposta.repository;

import br.com.zup.braz.rui.proposta.domain.Carteira;
import br.com.zup.braz.rui.proposta.domain.NomeCarteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, String> {
    Optional<Carteira> findByIdCartaoAndNomeCarteira(String idCartao, NomeCarteira nomeCarteira);
}
