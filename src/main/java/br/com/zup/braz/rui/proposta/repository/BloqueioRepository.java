package br.com.zup.braz.rui.proposta.repository;

import br.com.zup.braz.rui.proposta.domain.Bloqueio;
import br.com.zup.braz.rui.proposta.domain.StatusBloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, String> {
    Optional<Bloqueio> findByIdCartaoAndStatusIn(String idCartao, List<Enum> status);

    Bloqueio findAllByStatus(Enum<StatusBloqueio> status);
}
